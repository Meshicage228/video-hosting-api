package ru.clevertec.springbootsessionstarter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.springbootsessionstarter.service.BlackListProvider;
import ru.clevertec.springbootsessionstarter.service.DefaultPropertiesProvider;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class DefaultBlackListProvider implements BlackListProvider {
    private final DefaultPropertiesProvider defaultPropertiesProvider;

    @Override
    public Set<String> getBlackList() {
        log.info("get black list using default property provider {}", defaultPropertiesProvider.getBlackList());
        return defaultPropertiesProvider.getBlackList();
    }
}
