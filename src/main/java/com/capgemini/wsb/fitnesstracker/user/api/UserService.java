package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Updates an existing user.
     *
     * @param user The user to be updated
     * @return The updated user
     */
    User updateUser(User user);

    /**
     * Deletes a user based on their ID.
     *
     * @param userId The ID of the user to be deleted
     */
    void deleteUser(Long userId);
}