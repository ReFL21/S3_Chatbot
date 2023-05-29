package com.example.Chatbot.business.rating;

import com.example.Chatbot.domain.rating.Rating;

import java.util.Optional;

public interface GetRatingUseCase {
    Optional<Rating> getRating(Long ratingId);
}
