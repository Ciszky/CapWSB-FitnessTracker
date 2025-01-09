package com.capgemini.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User and UserDto.
 */
@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity
     * @return the UserDto
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto the UserDto
     * @return the User entity
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    /**
     * Updates a User entity with data from a UserDto.
     *
     * @param userDto the UserDto
     * @param user the User entity to update
     * @return the updated User entity
     */
    public User toUpdateEntity(UserDto userDto, User user) {
        if (userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }

        if (userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }

        if (userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }

        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        return user;
    }
}