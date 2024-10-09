package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.response.SubscriptionDtoResponse;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.mapper.ChannelMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.SubscriptionService;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    @Override
    @Transactional
    public void addSubscription(UUID userId, Long subscriptionId) {
        ChannelEntity channelEntity = channelRepository.findById(subscriptionId)
                .orElseThrow(ChannelNotFoundException::new);

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

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
    public Set<SubscriptionDtoResponse> getSubscriptions(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Set<ChannelEntity> subscriptions = userEntity.getSubscriptions();

        return channelMapper.toSubscriptionDtos(subscriptions);
    }
}
