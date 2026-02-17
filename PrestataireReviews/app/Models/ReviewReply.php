<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class ReviewReply extends Model
{
    use HasFactory, SoftDeletes;

    protected $fillable = [
        'review_id',
        'user_name',
        'user_email',
        'comment',
        'is_prestataire_reply',
    ];

    protected $casts = [
        'review_id' => 'integer',
        'is_prestataire_reply' => 'boolean',
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
    public function review()
    {
        return $this->belongsTo(Review::class);
    }

    // Accessors
    public function getTimeAgoAttribute()
    {
        return $this->created_at->diffForHumans();
    }
}