package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;

import io.micrometer.common.lang.Nullable;

/**
 * Data transfer object (DTO) representing a training.
 */
public record TrainingDto(
        @Nullable Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
    // No additional methods or fields required
}
