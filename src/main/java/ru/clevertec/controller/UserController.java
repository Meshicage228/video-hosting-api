package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.user.CreateUserDto;
import ru.clevertec.providers.BlackListFromBD;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.user.UpdatedUserDto;
import ru.clevertec.dto.user.UpdateUserDto;
import ru.clevertec.service.UserService;
import ru.clevertec.springbootsessionstarter.annotation.SessionProvider;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    @SessionProvider(blackListProviders = BlackListFromBD.class)
    public CreatedUserDto createUser(@RequestBody CreateUserDto createUserDto) {
        log.info("Current session : {}", createUserDto.getSession());
        return userService.saveUser(createUserDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    @SessionProvider(blackLists = {"Vlad", "Dmitry"})
    public UpdatedUserDto updateUser(@PathVariable Long userId,
                                     @RequestBody UpdateUserDto updateDto) {
        log.info("Current session : {}", updateDto.getSession());
        return userService.fullUpdateUser(userId, updateDto);
    }

    @PatchMapping("/{userId}")
    public UpdatedUserDto patchUpdateUser(@PathVariable Long userId, @RequestBody UpdateUserDto updateDto) {
        return userService.patchUpdateUser(userId, updateDto);
    }
}
