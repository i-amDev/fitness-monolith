package com.project.fitness_monolith.service;

import com.project.fitness_monolith.dto.ActivityRequest;
import com.project.fitness_monolith.dto.ActivityResponse;
import com.project.fitness_monolith.entity.Activity;
import com.project.fitness_monolith.entity.User;
import com.project.fitness_monolith.repository.ActivityRepository;
import com.project.fitness_monolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final UserRepository userRepository;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        User user = userRepository.findById(activityRequest.getUserId()).orElseThrow(() -> new RuntimeException("Invalid User " + activityRequest.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(activityRequest.getType())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .build();

        Activity savedActivity = activityRepository.save(activity);

        ActivityResponse activityResponse = ActivityResponse.builder()
                .id(savedActivity.getId())
                .userId(savedActivity.getUser().getId())
                .type(savedActivity.getType())
                .additionalMetrics(savedActivity.getAdditionalMetrics())
                .duration(savedActivity.getDuration())
                .caloriesBurned(savedActivity.getCaloriesBurned())
                .startTime(savedActivity.getStartTime())
                .build();

        return activityResponse;
    }

}
