package ru.clevertec.sessionservice.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.session.clean")
public class SchedulerLog {
    private boolean enabled;
    private String frequency = "0 0 * * * ?"; // "@daily"

    @PostConstruct
    public void log() {
        log.info("Scheduler properties : isWorking = {}, frequency = {}", enabled, frequency);
    }
}
