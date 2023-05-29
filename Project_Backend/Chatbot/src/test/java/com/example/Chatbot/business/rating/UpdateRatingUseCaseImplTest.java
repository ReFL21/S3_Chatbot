package com.example.Chatbot.business.rating;

import com.example.Chatbot.business.impl.nodes.UpdateNodesUseCaseImpl;
import com.example.Chatbot.business.impl.rating.UpdateRatingUseCaseImpl;
import com.example.Chatbot.domain.nodes.UpdateNodeRequest;
import com.example.Chatbot.domain.rating.UpdateRatingRequest;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateRatingUseCaseImplTest {
    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    UpdateRatingUseCaseImpl updateRatingUseCase;
    @Test
    void UpdateRatingHappyFlow(){
        RatingEntity beforeUpdateRating = RatingEntity.builder()
                .id(1L)
                .timesSatisfied(0)
                .timesReached(0)
                .timesUnsatisfied(0)
                .build();

        when(ratingRepository.findById(1L)).thenReturn(Optional.of(beforeUpdateRating));
        UpdateRatingRequest request = UpdateRatingRequest.builder()
                .id(1L)
                .wasItUseful(Boolean.TRUE)
                .build();
        updateRatingUseCase.updateRating(request);

        RatingEntity expectedRating = RatingEntity.builder()
                .id(1L)
                .timesSatisfied(1)
                .timesReached(0)
                .timesUnsatisfied(0)
                .build();
        Optional<RatingEntity> actualRating= ratingRepository.findById(1L);
        assert actualRating.orElse(null) != null;
        assertEquals(expectedRating.getId(),actualRating.orElse(null).getId());
        assertEquals(expectedRating.getTimesSatisfied(),actualRating.orElse(null).getTimesSatisfied());
        verify(ratingRepository, times(2)).findById(1L);
    }
}
