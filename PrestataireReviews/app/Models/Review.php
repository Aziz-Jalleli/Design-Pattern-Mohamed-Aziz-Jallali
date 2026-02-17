<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Review extends Model
{
    use HasFactory, SoftDeletes;

    protected $fillable = [
        'prestataire_id',
        'user_name',
        'user_email',
        'rating',
        'comment',
        'service_type',
        'is_verified',
        'helpful_count',
    ];

    protected $casts = [
        'prestataire_id' => 'integer',
        'rating' => 'decimal:2',
        'is_verified' => 'boolean',
        'helpful_count' => 'integer',
        'created_at' => 'datetime',
        'updated_at' => 'datetime',
    ];

    protected $hidden = [
        'user_email',
    ];

    protected $appends = [
        'time_ago',
    ];

    // Relationships
    public function replies()
    {
        return $this->hasMany(ReviewReply::class);
    }

    // Accessors
    public function getTimeAgoAttribute()
    {
        return $this->created_at->diffForHumans();
    }

    // Scopes
    public function scopeByPrestataire($query, $prestataireId)
    {
        return $query->where('prestataire_id', $prestataireId);
    }

    public function scopeVerified($query)
    {
        return $query->where('is_verified', true);
    }

    public function scopeMinRating($query, $rating)
    {
        return $query->where('rating', '>=', $rating);
    }
}