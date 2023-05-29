package com.example.Chatbot.business.rating;

import com.example.Chatbot.domain.rating.Rating;

import java.util.List;

public interface GetRatingsUseCase {
    List<Rating> getRatings();
}
