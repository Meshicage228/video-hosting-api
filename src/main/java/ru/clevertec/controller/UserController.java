package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.update.UserUpdateDto;
import ru.clevertec.providers.BlackListFromBD;
import ru.clevertec.service.UserService;
import ru.clevertec.springbootsessionstarter.annotation.SessionProvider;

import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @SessionProvider(blackListProviders = BlackListFromBD.class)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Current session : {}", userDto.getSession());
        UserDto savedUser = userService.saveUser(userDto);

        return ResponseEntity.status(CREATED)
                .body(savedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/{userId}")
    @SessionProvider(blackLists = {"Vlad", "Dmitry"})
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId,
                                              @RequestBody UserUpdateDto updateDto) {
        log.info("Current session : {}", updateDto.getSession());
        return ResponseEntity.ok(userService.fullUpdateUser(userId, updateDto));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> patchUpdateUser(@PathVariable UUID userId, @RequestBody Map<Object, Object> patch) {
        return ResponseEntity.ok(userService.patchUpdateUser(userId, patch));
    }
}
