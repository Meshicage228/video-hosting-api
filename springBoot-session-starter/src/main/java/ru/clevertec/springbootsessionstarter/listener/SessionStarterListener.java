package ru.clevertec.springbootsessionstarter.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.clevertec.springbootsessionstarter.service.DefaultPropertyProvider;

@Slf4j
@RequiredArgsConstructor
public class SessionStarterListener implements ApplicationListener<ContextRefreshedEvent> {
    private final DefaultPropertyProvider propertyProvider;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("SessionStarted started with blacklist : {}; propertyProviders : {}",
                propertyProvider.getBlackList(), propertyProvider.getPropertyProviders());
    }
}
