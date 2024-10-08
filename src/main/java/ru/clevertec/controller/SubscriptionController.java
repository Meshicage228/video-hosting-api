package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.response.SubscriptionDto;
import ru.clevertec.service.SubscriptionService;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{channelId}")
    private ResponseEntity<Void> subscribeChannel(@PathVariable("userId") UUID userId,
                                                  @PathVariable("channelId") Long channelId) {
        subscriptionService.addSubscription(userId, channelId);

        return ResponseEntity.status(CREATED)
                .build();
    }

    @DeleteMapping("/{channelId}")
    private ResponseEntity<Void> unsubscribeChannel(@PathVariable("userId") UUID userId,
                                                    @PathVariable("channelId") Long channelId) {
        subscriptionService.removeSubscription(userId, channelId);

        return ResponseEntity.status(NO_CONTENT)
                .build();
    }

    @GetMapping
    private ResponseEntity<Set<SubscriptionDto>> getSubscriptionTitles(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptions(userId));
    }
}
