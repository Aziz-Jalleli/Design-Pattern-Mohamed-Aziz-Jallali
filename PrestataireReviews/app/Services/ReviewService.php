<?php

namespace App\Services;

use App\Models\Review;
use App\Models\ReviewReply;
use Illuminate\Support\Facades\DB;

class ReviewService
{
    protected PrestataireService $prestataireService;

    public function __construct(PrestataireService $prestataireService)
    {
        $this->prestataireService = $prestataireService;
    }

    /**
     * Create a new review
     */
    public function createReview(array $data): Review
    {
        // Verify prestataire exists
        if (!$this->prestataireService->prestataireExists($data['prestataire_id'])) {
            throw new \Exception('Prestataire not found');
        }

        DB::beginTransaction();
        try {
            $review = Review::create($data);

            // Update average rating in Spring Boot service
            $this->updatePrestataireAverageRating($data['prestataire_id']);

            DB::commit();
            return $review->fresh(['replies']);
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }

    /**
     * Update a review
     */
    public function updateReview($id, array $data): Review
    {
        DB::beginTransaction();
        try {
            $review = Review::findOrFail($id);
            $review->update($data);

            // Recalculate average rating if rating was updated
            if (isset($data['rating'])) {
                $this->updatePrestataireAverageRating($review->prestataire_id);
            }

            DB::commit();
            return $review->fresh(['replies']);
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }

    /**
     * Delete a review
     */
    public function deleteReview($id): bool
    {
        DB::beginTransaction();
        try {
            $review = Review::findOrFail($id);
            $prestataireId = $review->prestataire_id;
            
            $review->delete();

            // Recalculate average rating
            $this->updatePrestataireAverageRating($prestataireId);

            DB::commit();
            return true;
        } catch (\Exception $e) {
            DB::rollBack();
            throw $e;
        }
    }

    /**
     * Mark review as helpful
     */
    public function markAsHelpful($id): Review
    {
        $review = Review::findOrFail($id);
        $review->increment('helpful_count');
        return $review->fresh(['replies']);
    }

    /**
     * Get review statistics for a prestataire
     */
    public function getReviewStatistics($prestataireId): array
    {
        $reviews = Review::byPrestataire($prestataireId)->get();

        $totalReviews = $reviews->count();
        $averageRating = $reviews->avg('rating') ?? 0;
        
        $ratingDistribution = [
            '5' => $reviews->where('rating', '>=', 4.5)->count(),
            '4' => $reviews->whereBetween('rating', [3.5, 4.49])->count(),
            '3' => $reviews->whereBetween('rating', [2.5, 3.49])->count(),
            '2' => $reviews->whereBetween('rating', [1.5, 2.49])->count(),
            '1' => $reviews->where('rating', '<', 1.5)->count(),
        ];

        $recentReviews = Review::byPrestataire($prestataireId)
            ->where('created_at', '>=', now()->subDays(30))
            ->count();

        return [
            'total_reviews' => $totalReviews,
            'average_rating' => round($averageRating, 2),
            'rating_distribution' => $ratingDistribution,
            'verified_reviews' => $reviews->where('is_verified', true)->count(),
            'recent_reviews' => $recentReviews,
        ];
    }

    /**
     * Get prestataire reviews with statistics
     */
    public function getPrestataireReviewsWithStats($prestataireId, $limit = 15, $page = 1)
    {
        $reviews = Review::byPrestataire($prestataireId)
            ->with('replies')
            ->orderBy('created_at', 'desc')
            ->paginate($limit, ['*'], 'page', $page);

        $statistics = $this->getReviewStatistics($prestataireId);

        return [
            'reviews' => $reviews->items(),
            'pagination' => [
                'total' => $reviews->total(),
                'per_page' => $reviews->perPage(),
                'current_page' => $reviews->currentPage(),
                'last_page' => $reviews->lastPage(),
                'has_more_pages' => $reviews->hasMorePages(),
            ],
            'statistics' => $statistics,
        ];
    }

    /**
     * Get top rated prestataires
     */
    public function getTopRatedPrestataires(int $limit = 10): array
    {
        // Get all prestataires with at least one review
        $prestataireIds = Review::select('prestataire_id')
            ->distinct()
            ->pluck('prestataire_id')
            ->toArray();

        $prestataires = $this->prestataireService->getPrestatairesByIds($prestataireIds);

        // Add review statistics to each prestataire
        $withStats = array_map(function ($prestataire) {
            $stats = $this->getReviewStatistics($prestataire['id']);
            $prestataire['review_statistics'] = $stats;
            return $prestataire;
        }, $prestataires);

        // Sort by average rating
        usort($withStats, function ($a, $b) {
            return $b['review_statistics']['average_rating'] <=> $a['review_statistics']['average_rating'];
        });

        return array_slice($withStats, 0, $limit);
    }

    /**
     * Update prestataire average rating
     */
    protected function updatePrestataireAverageRating($prestataireId): void
    {
        $averageRating = Review::byPrestataire($prestataireId)->avg('rating');
        
        if ($averageRating) {
            $this->prestataireService->updatePrestataireRating(
                $prestataireId, 
                round($averageRating, 2)
            );
        }
    }
}