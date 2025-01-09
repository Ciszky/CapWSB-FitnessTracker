package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * Controller for users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    /**
     * Get all users
     *
     * @return List of UserDto
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Get all users in simple format
     *
     * @return List of UserSimpleDto
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                          .stream()
                          .map(userSimpleMapper::toSimpleDto)
                          .toList();
    }

    /**
     * Get user by ID
     *
     * @param userId Long
     * @return UserDto
     */
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
    }

    /**
     * Get user by email
     *
     * @param email String
     * @return List of UserEmailSimpleDto
     */
    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmailIgnoreCase(email)
                            .stream()
                            .map(userEmailSimpleMapper::toEmailSimpleDto)
                            .toList();
    }

    /**
     * Get users older than a given date
     *
     * @param time LocalDate
     * @return List of UserDto
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Add a new user
     *
     * @param userDto UserDto
     * @return User
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return handleUserOperation(() -> {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
            return user;
        }, "Cannot add user with email: " + userDto.email());
    }

    /**
     * Update an existing user
     *
     * @param userId Long
     * @param userDto UserDto
     * @return User
     */
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return handleUserOperation(() -> {
            User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
            User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);
            return userService.updateUser(updatedUser);
        }, "Cannot update user with ID: " + userId);
    }

    /**
     * Delete an existing user
     *
     * @param userId Long
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        handleUserOperation(() -> {
            userService.deleteUser(userId);
            return null;
        }, "Cannot delete user with ID: " + userId);
    }

    private <T> T handleUserOperation(UserOperation<T> operation, String errorMessage) {
        try {
            return operation.execute();
        } catch (Exception e) {
            throw new IllegalArgumentException(errorMessage + " with error: " + e.getMessage());
        }
    }

    @FunctionalInterface
    private interface UserOperation<T> {
        T execute() throws Exception;
    }
}