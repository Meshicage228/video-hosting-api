package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.subscription.SubscriptionDto;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.mapper.ChannelMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.SubscriptionService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    @Override
    @Transactional
    public void addSubscription(Long userId, Long subscriptionId) {
        ChannelEntity channelEntity = channelRepository.findById(subscriptionId)
                .orElseThrow(() -> new ChannelNotFoundException(String.valueOf(subscriptionId)));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));

        userEntity.addSubscription(channelEntity);
    }

    @Override
    @Transactional
    public void removeSubscription(Long userId, Long subscriptionId) {
        ChannelEntity channelEntity = channelRepository.findById(subscriptionId)
                .orElseThrow(() -> new ChannelNotFoundException(String.valueOf(subscriptionId)));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));

        userEntity.removeSubscription(channelEntity);
    }

    @Override
    public Set<SubscriptionDto> getSubscriptions(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));

        Set<ChannelEntity> subscriptions = userEntity.getSubscriptions();

        return channelMapper.toSubscriptionDtos(subscriptions);
    }
}
