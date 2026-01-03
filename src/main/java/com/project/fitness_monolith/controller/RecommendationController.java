package com.project.fitness_monolith.controller;

import com.project.fitness_monolith.dto.RecommendationRequest;
import com.project.fitness_monolith.entity.Recommendation;
import com.project.fitness_monolith.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest recommendationRequest) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(recommendationRequest));
    }


}
