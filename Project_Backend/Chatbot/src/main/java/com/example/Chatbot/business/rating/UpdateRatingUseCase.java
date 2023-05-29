package com.example.Chatbot.business.rating;

import com.example.Chatbot.domain.rating.UpdateRatingRequest;

public interface UpdateRatingUseCase {
    void updateRating(UpdateRatingRequest request);
}
