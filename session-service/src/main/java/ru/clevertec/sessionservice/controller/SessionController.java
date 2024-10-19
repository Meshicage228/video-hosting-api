package ru.clevertec.sessionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.sessionservice.dto.SessionRequest;
import ru.clevertec.sessionservice.dto.SessionResponse;
import ru.clevertec.sessionservice.service.SessionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sessions")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping
    public SessionResponse getOrCreateAndGetSession(@Valid SessionRequest sessionRequest) {
        return sessionService.findSessionByLogin(sessionRequest.login());
    }
}
