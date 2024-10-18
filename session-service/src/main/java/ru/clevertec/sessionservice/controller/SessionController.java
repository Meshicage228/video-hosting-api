package ru.clevertec.sessionservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.sessionservice.dto.SessionResponse;
import ru.clevertec.sessionservice.entity.Session;
import ru.clevertec.sessionservice.service.SessionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sessions")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping
    public SessionResponse getOrCreateAndGetSession(@Valid @NotBlank(message = "should not be blank")
                                                        @NotNull(message = "Login not provided")
                                                        @RequestParam("login") String login) {
        return sessionService.findSessionByLogin(login);
    }
}
