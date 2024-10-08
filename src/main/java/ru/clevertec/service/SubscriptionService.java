package ru.clevertec.service;

import ru.clevertec.dto.response.SubscriptionDto;

import java.util.Set;
import java.util.UUID;

public interface SubscriptionService {
    void addSubscription(UUID userId, Long subscriptionId);

    void removeSubscription(UUID userId, Long subscriptionId);

    Set<SubscriptionDto> getSubscriptions(UUID userId);
}
