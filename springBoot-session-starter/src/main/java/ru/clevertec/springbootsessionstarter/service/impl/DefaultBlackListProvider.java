package ru.clevertec.springbootsessionstarter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.springbootsessionstarter.service.BlackListPropertiesProvider;
import ru.clevertec.springbootsessionstarter.service.DefaultPropertyProvider;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class DefaultBlackListProvider implements BlackListPropertiesProvider {
    private final DefaultPropertyProvider defaultPropertyProvider;

    @Override
    public Set<String> getBlackList() {
        log.info("get black list using default property provider {}", defaultPropertyProvider.getBlackList());
        return defaultPropertyProvider.getBlackList();
    }
}
