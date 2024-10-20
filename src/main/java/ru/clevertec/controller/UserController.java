package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.user.CreateUserDto;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.user.UpdatedUserDto;
import ru.clevertec.dto.user.UpdateUserDto;
import ru.clevertec.service.UserService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreatedUserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.saveUser(createUserDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public UpdatedUserDto updateUser(@PathVariable Long userId,
                                     @RequestBody UpdateUserDto updateDto) {
        return userService.fullUpdateUser(userId, updateDto);
    }

    @PatchMapping("/{userId}")
    public UpdatedUserDto patchUpdateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateDto) {
        return userService.patchUpdateUser(userId, updateDto);
    }
}
