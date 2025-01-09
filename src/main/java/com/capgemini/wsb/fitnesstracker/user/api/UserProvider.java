package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves a list of users whose email matches the given email, ignoring case.
     *
     * @param email The email of the users to be searched
     * @return A list of users with matching email
     */
    List<User> getUserByEmailIgnoreCase(String email);

    /**
     * Retrieves a list of users who are older than the given date.
     *
     * @param date The date to compare against
     * @return A list of users older than the given date
     */
    List<User> getUsersOlderThan(LocalDate date);

    /**
     * Retrieves all users.
     *
     * @return A list containing all users
     */
    List<User> findAllUsers();
}