package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.UserDto;
import ru.clevertec.service.UserService;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto channelDto){
        UserDto savedUser = userService.saveUser(channelDto);

        return ResponseEntity.status(CREATED)
                .body(savedUser);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId){
        userService.deleteUser(userId);

        return ResponseEntity.status(NO_CONTENT)
                .build();
    }

    @PutMapping("/{userId}")
    private ResponseEntity<Void> updateUser(@PathVariable("userId") UUID userId,
                                            @RequestBody UserDto channelDto){
       userService.fullUpdateUser(userId, channelDto);

       return ResponseEntity.status(NO_CONTENT)
               .build();
    }
}
