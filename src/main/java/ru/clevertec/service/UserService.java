package ru.clevertec.service;

import ru.clevertec.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    void fullUpdateUser(UUID userId, UserDto userDto);

    void deleteUser(UUID userId);
}
