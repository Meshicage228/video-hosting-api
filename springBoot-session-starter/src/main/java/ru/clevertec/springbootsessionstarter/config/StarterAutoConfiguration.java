package ru.clevertec.springbootsessionstarter.config;

import feign.codec.ErrorDecoder;
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
import ru.clevertec.springbootsessionstarter.clients.FeignExceptionDecoder;
import ru.clevertec.springbootsessionstarter.clients.SessionClient;
import ru.clevertec.springbootsessionstarter.listener.SessionStarterListener;
import ru.clevertec.springbootsessionstarter.service.DefaultPropertiesProvider;
import ru.clevertec.springbootsessionstarter.service.FeignPropertiesProvider;
import ru.clevertec.springbootsessionstarter.service.SessionService;
import ru.clevertec.springbootsessionstarter.service.impl.DefaultBlackListProvider;

@EnableAsync
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@AutoConfiguration
@EnableFeignClients(basePackageClasses = SessionClient.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@EnableConfigurationProperties(value = {DefaultPropertiesProvider.class, FeignPropertiesProvider.class})
@ConditionalOnProperty(prefix = "session.provider", value = "enabled", havingValue = "true")
public class StarterAutoConfiguration {
    @Bean
    public SessionHandlerPostProcessor postProcessor() {
        return new SessionHandlerPostProcessor();
    }

    @Bean
    public DefaultBlackListProvider getDefaultBlackListProvider(DefaultPropertiesProvider propertyProvider) {
        return new DefaultBlackListProvider(propertyProvider);
    }

    @Bean
    public SessionStarterListener sessionStarterListener(DefaultPropertiesProvider defaultPropertiesProvider,
                                                         FeignPropertiesProvider feignPropertiesProvider) {
        return new SessionStarterListener(defaultPropertiesProvider, feignPropertiesProvider);
    }

    @Bean
    public SessionService sessionService(SessionClient sessionClient) {
        return new SessionService(sessionClient);
    }

    @Bean
    public ErrorDecoder feignExceptionDecoder(){
        return new FeignExceptionDecoder();
    }
}
