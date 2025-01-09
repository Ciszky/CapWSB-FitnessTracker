package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;

import io.micrometer.common.lang.Nullable;

/**
 * Data transfer object (DTO) representing a training with user ID.
 */
public record TrainingDtoWithUserId(
        @Nullable Long id,
        Long userId,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
    // No additional methods or fields required
}
