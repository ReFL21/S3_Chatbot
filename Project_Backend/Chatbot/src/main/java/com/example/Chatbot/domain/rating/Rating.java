package com.example.Chatbot.domain.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Rating {
    private Long id;
    private Long nodeId;
    private int timesReached;
    private int timesSatisfied;
    private int timesUnsatisfied;
}
