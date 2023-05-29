package com.example.Chatbot.business.impl.rating;

import com.example.Chatbot.business.rating.UpdateRatingUseCase;
import com.example.Chatbot.domain.rating.UpdateRatingRequest;
import com.example.Chatbot.repository.RatingRepository;
import com.example.Chatbot.repository.impl.RatingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateRatingUseCaseImpl implements UpdateRatingUseCase {
    private final RatingRepository ratingRepository;

    @Override
    public void updateRating(UpdateRatingRequest request) {
        Optional<RatingEntity> ratingOptional= ratingRepository.findById(request.getId());
        RatingEntity rating = ratingOptional.get();
        updateFields(request,rating);
    }
    private void updateFields(UpdateRatingRequest request, RatingEntity rating){
        if(request.getWasItUseful()==null) {
            rating.setTimesReached(rating.getTimesReached()+1);
        }else if(!request.getWasItUseful()){
            rating.setTimesUnsatisfied(rating.getTimesUnsatisfied()+1);
        }else{
            rating.setTimesSatisfied(rating.getTimesSatisfied()+1);
        }
        ratingRepository.save(rating);
    }
}
