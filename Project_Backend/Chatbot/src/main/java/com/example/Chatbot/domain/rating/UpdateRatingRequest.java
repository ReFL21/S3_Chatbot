package com.example.Chatbot.domain.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatingRequest {
    private Long id;
    private Boolean wasItUseful;
}
