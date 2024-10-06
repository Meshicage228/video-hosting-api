package ru.clevertec.service;

import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.update.UserUpdateDto;

import java.util.UUID;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    UserDto fullUpdateUser(UUID userId, UserUpdateDto userDto);

    void deleteUser(UUID userId);
}
