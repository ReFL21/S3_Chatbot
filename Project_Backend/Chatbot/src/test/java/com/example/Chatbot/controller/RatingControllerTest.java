package com.example.Chatbot.controller;

import com.example.Chatbot.business.rating.GetRatingUseCase;
import com.example.Chatbot.business.rating.GetRatingsUseCase;
import com.example.Chatbot.business.rating.UpdateRatingUseCase;
import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.domain.rating.UpdateRatingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private GetRatingsUseCase getRatingsUseCase;
    @MockBean
    private GetRatingUseCase getRatingUseCase;
    @MockBean
    private UpdateRatingUseCase updateRatingUseCase;
    //@Test
    void getAllRating_ShouldReturnListOfRatings() throws Exception {
        Rating rating1 = Rating.builder()
                .id(1L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();
        Rating rating2 = Rating.builder()
                .id(2L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();
        List<Rating> ratings = List.of(rating1, rating2);

        when(getRatingsUseCase.getRatings()).thenReturn(ratings);
        mockMvc.perform(MockMvcRequestBuilders.get("/ratings"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                    {
                    "ratings": [
                    {
                    "id": 1,
                    "timesSatisfied": 2,
                    "timesReached": 3,
                    "timesUnsatisfied": 0
                    },
                    {
                    "id": 2,
                    "timesSatisfied": 2,
                    "timesReached": 3,
                    "timesUnsatisfied": 0
                    }
                    ]
                    }
                    """
                ));

    }

    //@Test
    void getRating_shouldReturnVallidRating() throws Exception {
        Rating rating1 = Rating.builder()
                .id(1L)
                .timesSatisfied(2)
                .timesReached(3)
                .timesUnsatisfied(0)
                .build();

        when(getRatingUseCase.getRating(1L)).thenReturn(Optional.of(rating1));
        mockMvc.perform(get("/ratings/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                            {
                            "id": 1,
                            "timesSatisfied": 2,
                            "timesReached": 3,
                            "timesUnsatisfied": 0
                            }
                            """
                ));
        verify(getRatingUseCase).getRating(1L);
    }


   // @Test
    void updateRating_shouldReturn204() throws Exception {

        mockMvc.perform(put("/ratings/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                        "id": 1,
                            "timesSatisfied": 2,
                            "timesReached": 3,
                            "timesUnsatisfied": 0
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());
        UpdateRatingRequest expectedRating = UpdateRatingRequest.builder()
                .id(1L)
                .wasItUseful(true)
                .build();
        verify(updateRatingUseCase).updateRating(expectedRating);
    }

}
