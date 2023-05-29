package com.example.Chatbot.controller;

import com.example.Chatbot.business.rating.GetRatingUseCase;
import com.example.Chatbot.business.rating.GetRatingsUseCase;
import com.example.Chatbot.business.rating.UpdateRatingUseCase;
import com.example.Chatbot.domain.nodes.GetNodesRequest;
import com.example.Chatbot.domain.nodes.GetNodesResponse;
import com.example.Chatbot.domain.nodes.UpdateNodeRequest;
import com.example.Chatbot.domain.rating.Rating;
import com.example.Chatbot.domain.rating.UpdateRatingRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RatingController {
    private final GetRatingUseCase getRatingUseCase;
    private final GetRatingsUseCase getRatingsUseCase;
    private final UpdateRatingUseCase updateRatingUseCase;
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        List<Rating> ratings=getRatingsUseCase.getRatings();
        return ResponseEntity.ok(ratings);
    }
    @GetMapping("/find{id}")
    public ResponseEntity<Rating> getRating(@RequestParam(value = "id", required = false) Long id) {
        final Optional<Rating> ratingOptional=getRatingUseCase.getRating(id);
        if(ratingOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(ratingOptional.get());
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateRating(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateRatingRequest request) {
        request.setId(id);
        updateRatingUseCase.updateRating(request);
        return ResponseEntity.noContent().build();
    }
}
