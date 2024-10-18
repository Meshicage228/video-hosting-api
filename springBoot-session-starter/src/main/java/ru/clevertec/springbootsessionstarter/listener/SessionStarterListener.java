package ru.clevertec.springbootsessionstarter.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.clevertec.springbootsessionstarter.service.DefaultPropertiesProvider;
import ru.clevertec.springbootsessionstarter.service.FeignPropertiesProvider;

@Slf4j
@RequiredArgsConstructor
public class SessionStarterListener implements ApplicationListener<ContextRefreshedEvent> {
    private final DefaultPropertiesProvider propertyProvider;
    private final FeignPropertiesProvider feignProperties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("SessionStarted started with blacklist from project properties : {}; propertyProviders : {}",
                propertyProvider.getBlackList(), propertyProvider.getPropertyProviders());

        log.info("Feign client started with name : {}; url : {}, path : {}" ,
                feignProperties.getName(), feignProperties.getUrl(), feignProperties.getPath());
    }
}
