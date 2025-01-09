package com.capgemini.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.user.api.User;

/**
 * Mapper for converting between User and UserSimpleDto.
 */
@Component
class UserSimpleMapper {

    /**
     * Converts a User entity to a UserSimpleDto.
     *
     * @param user the User entity
     * @return the UserSimpleDto
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Converts a UserSimpleDto to a User entity.
     *
     * @param userDto the UserSimpleDto
     * @return the User entity
     */
    User toSimpleEntity(UserSimpleDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }
}
