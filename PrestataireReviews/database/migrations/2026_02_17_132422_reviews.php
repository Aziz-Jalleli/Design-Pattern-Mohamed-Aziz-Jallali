<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('reviews', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('prestataire_id');
            $table->string('user_name');
            $table->string('user_email');
            $table->decimal('rating', 3, 2);
            $table->text('comment');
            $table->string('service_type')->nullable();
            $table->boolean('is_verified')->default(false);
            $table->unsignedInteger('helpful_count')->default(0);
            $table->timestamps();
            $table->softDeletes();

            // Indexes
            $table->index('prestataire_id');
            $table->index('rating');
            $table->index('is_verified');
            $table->index('created_at');
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('reviews');
    }
};