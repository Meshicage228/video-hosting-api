package ru.clevertec.springbootsessionstarter.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.springbootsessionstarter.clients.SessionClient;
import ru.clevertec.springbootsessionstarter.dto.Session;

@RequiredArgsConstructor
public class SessionService {
    private final SessionClient sessionClient;

    public Session getOrCreateAndGetSession(String login) {
        return sessionClient.getOrCreateAndGetSession(login);
    }
}
