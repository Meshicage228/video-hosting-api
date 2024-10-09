package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.response.SubscriptionDtoResponse;
import ru.clevertec.service.SubscriptionService;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{channelId}")
    public ResponseEntity<Void> subscribeChannel(@PathVariable UUID userId,
                                                  @PathVariable Long channelId) {
        subscriptionService.addSubscription(userId, channelId);

        return ResponseEntity.status(CREATED)
                .build();
    }

    @DeleteMapping("/{channelId}")
    public ResponseEntity<Void> unsubscribeChannel(@PathVariable UUID userId,
                                                    @PathVariable Long channelId) {
        subscriptionService.removeSubscription(userId, channelId);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Set<SubscriptionDtoResponse>> getSubscriptionTitles(@PathVariable UUID userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptions(userId));
    }
}
