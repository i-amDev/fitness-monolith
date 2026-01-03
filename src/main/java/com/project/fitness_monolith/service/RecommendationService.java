package com.project.fitness_monolith.service;

import com.project.fitness_monolith.dto.RecommendationRequest;
import com.project.fitness_monolith.entity.Activity;
import com.project.fitness_monolith.entity.Recommendation;
import com.project.fitness_monolith.entity.User;
import com.project.fitness_monolith.repository.ActivityRepository;
import com.project.fitness_monolith.repository.RecommendationRepository;
import com.project.fitness_monolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    private final UserRepository userRepository;

    private final ActivityRepository activityRepository;

    public Recommendation generateRecommendation(RecommendationRequest recommendationRequest) {
        User user = userRepository.findById(recommendationRequest.getUserId()).orElseThrow(() -> new RuntimeException("Invalid User " + recommendationRequest.getUserId()));

        Activity activity = activityRepository.findById(recommendationRequest.getActivityId()).orElseThrow(() -> new RuntimeException("Invalid Activity " + recommendationRequest.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(recommendationRequest.getImprovements())
                .suggestions(recommendationRequest.getSuggestions())
                .safety(recommendationRequest.getSafety())
                .build();

        return recommendationRepository.save(recommendation);
    }
}
