package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.SubscriptionService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public void addSubscription(UUID userId, Long subscriptionId) {
        ChannelEntity channelEntity = channelRepository.findById(subscriptionId).get();
        UserEntity userEntity = userRepository.findById(userId).get();

        userEntity.addSubscription(channelEntity);
    }

    @Override
    @Transactional
    public void removeSubscription(UUID userId, Long subscriptionId) {
        ChannelEntity channelEntity = channelRepository.findById(subscriptionId)
                .orElseThrow(ChannelNotFoundException::new);

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userEntity.removeSubscription(channelEntity);
    }

    @Override
    public List<String> getSubscriptionTitles(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return channelRepository.findChannelTitlesBySubscriber(userId);
    }
}
