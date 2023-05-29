package com.example.Chatbot.business.rating;

import com.example.Chatbot.business.impl.rating.GetRatingUseCaseImpl;
import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.repository.RatingRepository;
import com.example.Chatbot.repository.impl.NodeEntity;
import com.example.Chatbot.repository.impl.RatingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetRatingUseCaseImplTest {
    @Mock
    RatingRepository ratingRepository;
    @InjectMocks
    GetRatingUseCaseImpl getRatingUseCase;

    @Test
    void getRating_shouldReturnRatingWhenValid(){

        RatingEntity rating1 = RatingEntity.builder()
                .id(1L)
                .node(NodeEntity.builder().id(1L).build())
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();

        when(ratingRepository.findById(1L)).thenReturn(Optional.of(rating1));
        Optional<Rating> ratingOptional = getRatingUseCase.getRating(1L);
        Rating actualRating = ratingOptional.orElseThrow();
        Rating expectedRating = Rating.builder()
                .id(1L)
                .nodeId(1L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();

        assertEquals(expectedRating, actualRating);
        verify(ratingRepository).findById(1L);
    }


}
