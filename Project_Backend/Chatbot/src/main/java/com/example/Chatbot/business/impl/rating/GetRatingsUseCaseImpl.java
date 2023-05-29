package com.example.Chatbot.business.impl.rating;

import com.example.Chatbot.business.rating.GetRatingsUseCase;
import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.repository.RatingRepository;
import com.example.Chatbot.repository.impl.RatingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetRatingsUseCaseImpl implements GetRatingsUseCase {
    private final RatingRepository ratingRepository;
    @Override
    public List<Rating> getRatings() {
        List<RatingEntity> results= ratingRepository.findAll();
        List<Rating> ratings = results
                .stream()
                .map(RatingConverter::convert)
                .toList();
        return ratings;
    }
}
