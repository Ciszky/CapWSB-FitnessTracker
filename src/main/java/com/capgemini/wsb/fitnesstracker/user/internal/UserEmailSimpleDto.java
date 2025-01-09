package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * A simple DTO for user email information.
 *
 * @param id the ID of the user, can be null
 * @param email the email of the user
 */
public record UserEmailSimpleDto(@Nullable Long id, String email) {
    // No additional methods or fields required
}
