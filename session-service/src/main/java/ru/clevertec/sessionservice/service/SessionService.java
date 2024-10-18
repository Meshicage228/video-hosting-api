package ru.clevertec.sessionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.sessionservice.dto.SessionRequest;
import ru.clevertec.sessionservice.dto.SessionResponse;
import ru.clevertec.sessionservice.mapper.SessionMapper;
import ru.clevertec.sessionservice.repository.SessionRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    public SessionResponse findSessionByLogin(String login) {
        return sessionRepository.findByLogin(login)
                .map(sessionMapper::fromSessionToResponse)
                .orElseGet(() -> createSession(login));
    }

    private SessionResponse createSession(String login) {
        return Optional.of(new SessionRequest(login))
                .map(sessionMapper::fromRequestToSession)
                .map(sessionRepository::save)
                .map(sessionMapper::fromSessionToResponse)
                .orElseThrow(RuntimeException::new);
    }

    public void cleanAllExpiredSessions() {
        sessionRepository.deleteByCreationDateBefore(LocalDate.now());
    }
}
