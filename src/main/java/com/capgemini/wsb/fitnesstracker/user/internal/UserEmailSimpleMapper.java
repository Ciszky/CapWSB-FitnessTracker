package com.capgemini.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.user.api.User;

/**
 * Mapper for converting between User and UserEmailSimpleDto.
 */
@Component
class UserEmailSimpleMapper {

    /**
     * Converts a User entity to a UserEmailSimpleDto.
     *
     * @param user the User entity
     * @return the UserEmailSimpleDto
     */
    UserEmailSimpleDto toEmailSimpleDto(User user) {
        return new UserEmailSimpleDto(user.getId(), user.getEmail());
    }

    /**
     * Converts a UserEmailSimpleDto to a User entity.
     *
     * @param userDto the UserEmailSimpleDto
     * @return the User entity
     */
    User toSimpleEmailEntity(UserEmailSimpleDto userDto) {
        return new User(null, null, null, userDto.email());
    }
}
