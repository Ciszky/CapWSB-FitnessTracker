package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.wsb.fitnesstracker.user.api.User;

/**
 * Repository interface for managing User entities.
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email.
     *
     * @param email the email of the user to search
     * @return an Optional containing the found user or Optional.empty() if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Finds users by a fragment of their email, ignoring case.
     *
     * @param emailFragment the fragment of the email to search
     * @return a list of users whose email contains the fragment
     */
    default List<User> findByEmailFragmentIgnoreCase(String emailFragment) {
        return findAll().stream()
                        .filter(user -> user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                        .toList();
    }

    /**
     * Finds users whose birthdate is before a specified date.
     *
     * @param date the date to compare
     * @return a list of users whose birthdate is before the specified date
     */
    default List<User> findByBirthDateBefore(LocalDate date) {
        return findAll().stream()
                        .filter(user -> user.getBirthdate().isBefore(date))
                        .toList();
    }
}
