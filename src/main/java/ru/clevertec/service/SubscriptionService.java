package ru.clevertec.service;

import ru.clevertec.dto.response.SubscriptionDtoResponse;

import java.util.Set;
import java.util.UUID;

public interface SubscriptionService {
    void addSubscription(UUID userId, Long subscriptionId);

    void removeSubscription(UUID userId, Long subscriptionId);

    Set<SubscriptionDtoResponse> getSubscriptions(UUID userId);
}
