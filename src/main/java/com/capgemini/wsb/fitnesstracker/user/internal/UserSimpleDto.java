package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * A simple DTO for user information.
 *
 * @param id the ID of the user
 * @param firstName the first name of the user
 * @param lastName the last name of the user
 */
public record UserSimpleDto(Long id, String firstName, String lastName) {
    // No additional methods or fields required
}
