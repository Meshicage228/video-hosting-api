package ru.clevertec.service;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    void addSubscription(UUID userId, Long subscriptionId);

    void removeSubscription(UUID userId, Long subscriptionId);

    List<String> getSubscriptionTitles(UUID channelId);
}
