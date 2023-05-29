package com.example.Chatbot.business.impl.rating;

import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.repository.impl.RatingEntity;

public class RatingConverter {
    private RatingConverter(){

    }
    public static Rating convert(RatingEntity rating){
        return Rating.builder()
                .id(rating.getId())
                .nodeId(rating.getId())
                .timesReached(rating.getTimesReached())
                .timesSatisfied(rating.getTimesSatisfied())
                .timesUnsatisfied(rating.getTimesUnsatisfied())
                .build();
    }
}
