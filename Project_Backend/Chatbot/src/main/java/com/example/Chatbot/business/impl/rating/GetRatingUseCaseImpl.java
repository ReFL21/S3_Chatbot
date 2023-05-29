package com.example.Chatbot.business.impl.rating;

import com.example.Chatbot.business.rating.GetRatingUseCase;
import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetRatingUseCaseImpl implements GetRatingUseCase {
    private final RatingRepository ratingRepository;

    @Override
    public Optional<Rating> getRating(Long ratingId) {
        return ratingRepository.findById(ratingId).map(RatingConverter::convert);
    }
}
