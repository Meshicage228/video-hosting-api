package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.subscription.SubscriptionDto;
import ru.clevertec.service.SubscriptionService;

import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{channelId}")
    @ResponseStatus(CREATED)
    public void subscribeChannel(@PathVariable Long userId,
                                 @PathVariable Long channelId) {
        subscriptionService.addSubscription(userId, channelId);
    }

    @DeleteMapping("/{channelId}")
    @ResponseStatus(NO_CONTENT)
    public void unsubscribeChannel(@PathVariable Long userId,
                                   @PathVariable Long channelId) {
        subscriptionService.removeSubscription(userId, channelId);
    }

    @GetMapping
    public Set<SubscriptionDto> getSubscriptionTitles(@PathVariable Long userId) {
        return subscriptionService.getSubscriptions(userId);
    }
}
