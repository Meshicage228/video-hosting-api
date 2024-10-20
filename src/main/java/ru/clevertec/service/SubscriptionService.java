package ru.clevertec.service;

import ru.clevertec.dto.subscription.SubscriptionDto;

import java.util.Set;

public interface SubscriptionService {
    void addSubscription(Long userId, Long subscriptionId);

    void removeSubscription(Long userId, Long subscriptionId);

    Set<SubscriptionDto> getSubscriptions(Long userId);
}
