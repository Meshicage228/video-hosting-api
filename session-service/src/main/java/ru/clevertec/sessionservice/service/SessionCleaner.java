package ru.clevertec.sessionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.session.clean", name = "enabled", havingValue = "true")
public class SessionCleaner {
    private final SessionService sessionService;

    @Scheduled(cron = "#{schedulerLog.frequency}")
    public void cleanSessions() {
        sessionService.cleanAllExpiredSessions();
    }
}
