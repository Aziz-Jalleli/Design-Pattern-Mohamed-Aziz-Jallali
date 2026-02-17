<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('review_replies', function (Blueprint $table) {
            $table->id();
            $table->foreignId('review_id')->constrained()->onDelete('cascade');
            $table->string('user_name');
            $table->string('user_email');
            $table->text('comment');
            $table->boolean('is_prestataire_reply')->default(false);
            $table->timestamps();
            $table->softDeletes();

            // Indexes
            $table->index('review_id');
            $table->index('is_prestataire_reply');
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('review_replies');
    }
};