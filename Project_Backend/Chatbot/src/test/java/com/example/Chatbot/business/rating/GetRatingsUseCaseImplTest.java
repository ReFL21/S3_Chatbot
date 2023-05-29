package com.example.Chatbot.business.rating;

import com.example.Chatbot.business.impl.rating.GetRatingsUseCaseImpl;
import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.repository.RatingRepository;
import com.example.Chatbot.repository.impl.RatingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetRatingsUseCaseImplTest {
    @Mock
    RatingRepository ratingRepository;
    @InjectMocks
    GetRatingsUseCaseImpl getRatingsUseCase;

    @Test
    void getAllRatingsShouldReturnList(){
        RatingEntity rating1 = RatingEntity.builder()
                .id(1L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();
        RatingEntity rating2 = RatingEntity.builder()
                .id(2L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();

        when(ratingRepository.findAll()).thenReturn(List.of(rating1, rating2));
       List<Rating> actualList = getRatingsUseCase.getRatings();

        Rating rating1DTO = Rating.builder()
                .id(1L)
                .nodeId(1L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();
        Rating rating2DTO = Rating.builder()
                .id(2L)
                .nodeId(2L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();

        List<Rating> expectedList = List.of(rating1DTO, rating2DTO);

        assertEquals(expectedList, actualList);
        verify(ratingRepository).findAll();

    }
}
