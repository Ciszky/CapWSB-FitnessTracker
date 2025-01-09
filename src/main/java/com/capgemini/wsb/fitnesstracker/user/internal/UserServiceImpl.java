package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for managing users.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param user the user to update
     * @return the updated user
     */
    @Override
    public User updateUser(final User user) {
        log.info("Updating User {}", user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("User has no DB ID, create is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId the ID of the user to delete
     */
    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

    /**
     * Finds a user by ID.
     *
     * @param userId the ID of the user
     * @return an Optional containing the found user or Optional.empty() if none matched
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Finds users older than a specified date.
     *
     * @param date the date to compare
     * @return a list of users whose birthdate is before the specified date
     */
    @Override
    public List<User> getUsersOlderThan(LocalDate date) {
        return userRepository.findByBirthDateBefore(date);
    }

    /**
     * Finds a user by email.
     *
     * @param email the email of the user
     * @return an Optional containing the found user or Optional.empty() if none matched
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds users by email, ignoring case.
     *
     * @param email the email of the user
     * @return a list of users whose email matches the given email
     */
    @Override
    public List<User> getUserByEmailIgnoreCase(final String email) {
        return userRepository.findByEmailFragmentIgnoreCase(email);
    }

    /**
     * Finds all users.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}