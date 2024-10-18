package ru.clevertec.springbootsessionstarter.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "session.provider")
public class DefaultPropertiesProvider {
    private boolean enabled = true;
    private Set<String> blackList = new HashSet<>();
    private Set<Class<? extends BlackListProvider>> propertyProviders = new HashSet<>();
}