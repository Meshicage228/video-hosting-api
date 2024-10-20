package ru.clevertec.service;

import ru.clevertec.dto.user.CreateUserDto;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.user.UpdateUserDto;
import ru.clevertec.dto.user.UpdatedUserDto;

public interface UserService {
    CreatedUserDto saveUser(CreateUserDto userDto);

    UpdatedUserDto fullUpdateUser(Long userId, UpdateUserDto userDto);

    void deleteUser(Long userId);

    UpdatedUserDto patchUpdateUser(Long userId, UpdateUserDto updateDto);
}
