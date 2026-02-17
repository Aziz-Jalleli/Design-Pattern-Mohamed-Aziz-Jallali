<?php

namespace App\GraphQL\Queries;

use App\Models\Review;
use App\Services\ReviewService;
use App\Services\PrestataireService;

class ReviewQueries
{
    protected ReviewService $reviewService;
    protected PrestataireService $prestataireService;

    public function __construct(ReviewService $reviewService, PrestataireService $prestataireService)
    {
        $this->reviewService = $reviewService;
        $this->prestataireService = $prestataireService;
    }

    /**
     * Get prestataire reviews with statistics
     */
    public function prestataireReviews($rootValue, array $args)
    {
        $prestataireId = $args['prestataire_id'];
        $limit = $args['limit'] ?? 15;
        $page = $args['page'] ?? 1;

        $data = $this->reviewService->getPrestataireReviewsWithStats($prestataireId, $limit, $page);

        return [
            'reviews' => $data['reviews'],
            'pagination' => $data['pagination'],
            'statistics' => $this->formatStatistics($data['statistics']),
        ];
    }

    /**
     * Get review statistics
     */
    public function reviewStatistics($rootValue, array $args)
    {
        $prestataireId = $args['prestataire_id'];
        $statistics = $this->reviewService->getReviewStatistics($prestataireId);
        
        return $this->formatStatistics($statistics);
    }

    /**
     * Get top rated prestataires
     */
    public function topRatedPrestataires($rootValue, array $args)
    {
        $limit = $args['limit'] ?? 10;
        $prestataires = $this->reviewService->getTopRatedPrestataires($limit);

        return array_map(function ($prestataire) {
            return [
                'id' => $prestataire['id'],
                'nom' => $prestataire['nom'],
                'telephone' => $prestataire['telephone'] ?? null,
                'note' => $prestataire['note'] ?? null,
                'experience' => $prestataire['experience'] ?? null,
                'disponible' => $prestataire['disponible'] ?? false,
                'review_statistics' => $this->formatStatistics($prestataire['review_statistics']),
            ];
        }, $prestataires);
    }

    /**
     * Format statistics for GraphQL response
     */
    protected function formatStatistics(array $statistics): array
    {
        return [
            'total_reviews' => $statistics['total_reviews'],
            'average_rating' => $statistics['average_rating'],
            'rating_distribution' => [
                'five_stars' => $statistics['rating_distribution']['5'],
                'four_stars' => $statistics['rating_distribution']['4'],
                'three_stars' => $statistics['rating_distribution']['3'],
                'two_stars' => $statistics['rating_distribution']['2'],
                'one_star' => $statistics['rating_distribution']['1'],
            ],
            'verified_reviews' => $statistics['verified_reviews'],
            'recent_reviews' => $statistics['recent_reviews'],
        ];
    }
}