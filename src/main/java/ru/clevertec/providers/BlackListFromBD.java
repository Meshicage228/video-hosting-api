package ru.clevertec.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.entity.BlackListRepository;
import ru.clevertec.springbootsessionstarter.service.BlackListProvider;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class BlackListFromBD implements BlackListProvider {
    private final BlackListRepository repository;

    @Override
    public Set<String> getBlackList() {
        return repository.getAllBlockedLogins();
    }
}
