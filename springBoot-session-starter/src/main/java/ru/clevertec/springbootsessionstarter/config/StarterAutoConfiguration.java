package ru.clevertec.springbootsessionstarter.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.clevertec.springbootsessionstarter.bdd.SessionHandlerPostProcessor;
import ru.clevertec.springbootsessionstarter.clients.SessionClient;
import ru.clevertec.springbootsessionstarter.listener.SessionStarterListener;
import ru.clevertec.springbootsessionstarter.service.DefaultPropertyProvider;
import ru.clevertec.springbootsessionstarter.service.FeignPropertiesProvider;
import ru.clevertec.springbootsessionstarter.service.SessionService;

@EnableAsync
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@AutoConfiguration
@EnableFeignClients(basePackageClasses = SessionClient.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@EnableConfigurationProperties(value = {DefaultPropertyProvider.class, FeignPropertiesProvider.class})
@ConditionalOnProperty(prefix = "session.provider", value = "enabled", havingValue = "true")
public class StarterAutoConfiguration {
    @Bean
    public SessionHandlerPostProcessor postProcessor() {
        return new SessionHandlerPostProcessor();
    }

    @Bean
    public SessionStarterListener sessionStarterListener(DefaultPropertyProvider defaultPropertyProvider) {
        return new SessionStarterListener(defaultPropertyProvider);
    }

    @Bean
    public SessionService sessionService(SessionClient sessionClient) {
        return new SessionService(sessionClient);
    }
}
