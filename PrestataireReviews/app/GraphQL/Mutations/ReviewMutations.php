<?php

namespace App\GraphQL\Mutations;

use App\Models\Review;
use App\Models\ReviewReply;
use App\Services\ReviewService;
use GraphQL\Error\Error;

class ReviewMutations
{
    protected ReviewService $reviewService;

    public function __construct(ReviewService $reviewService)
    {
        $this->reviewService = $reviewService;
    }

    /**
     * Create a new review
     */
    public function createReview($rootValue, array $args)
    {
        try {
            $review = $this->reviewService->createReview($args['input']);

            return [
                'review' => $review,
                'message' => 'Review created successfully',
                'success' => true,
            ];
        } catch (\Exception $e) {
            return [
                'review' => null,
                'message' => $e->getMessage(),
                'success' => false,
            ];
        }
    }

    /**
     * Update a review
     */
    public function updateReview($rootValue, array $args)
    {
        try {
            $review = $this->reviewService->updateReview($args['id'], $args['input']);

            return [
                'review' => $review,
                'message' => 'Review updated successfully',
                'success' => true,
            ];
        } catch (\Exception $e) {
            return [
                'review' => null,
                'message' => $e->getMessage(),
                'success' => false,
            ];
        }
    }

    /**
     * Delete a review
     */
    public function deleteReview($rootValue, array $args)
    {
        try {
            $this->reviewService->deleteReview($args['id']);

            return [
                'success' => true,
                'message' => 'Review deleted successfully',
            ];
        } catch (\Exception $e) {
            return [
                'success' => false,
                'message' => $e->getMessage(),
            ];
        }
    }

    /**
     * Mark review as helpful
     */
    public function markReviewAsHelpful($rootValue, array $args)
    {
        try {
            $review = $this->reviewService->markAsHelpful($args['id']);

            return [
                'review' => $review,
                'message' => 'Review marked as helpful',
                'success' => true,
            ];
        } catch (\Exception $e) {
            return [
                'review' => null,
                'message' => $e->getMessage(),
                'success' => false,
            ];
        }
    }

    /**
     * Create a review reply
     */
    public function createReviewReply($rootValue, array $args)
    {
        try {
            $reply = ReviewReply::create($args['input']);

            return [
                'reply' => $reply,
                'message' => 'Reply created successfully',
                'success' => true,
            ];
        } catch (\Exception $e) {
            return [
                'reply' => null,
                'message' => $e->getMessage(),
                'success' => false,
            ];
        }
    }

    /**
     * Update a review reply
     */
    public function updateReviewReply($rootValue, array $args)
    {
        try {
            $reply = ReviewReply::findOrFail($args['id']);
            $reply->update($args['input']);

            return [
                'reply' => $reply->fresh(),
                'message' => 'Reply updated successfully',
                'success' => true,
            ];
        } catch (\Exception $e) {
            return [
                'reply' => null,
                'message' => $e->getMessage(),
                'success' => false,
            ];
        }
    }

    /**
     * Delete a review reply
     */
    public function deleteReviewReply($rootValue, array $args)
    {
        try {
            $reply = ReviewReply::findOrFail($args['id']);
            $reply->delete();

            return [
                'success' => true,
                'message' => 'Reply deleted successfully',
            ];
        } catch (\Exception $e) {
            return [
                'success' => false,
                'message' => $e->getMessage(),
            ];
        }
    }
}