package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.channel.*;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.mapper.ChannelMapper;
import ru.clevertec.mapper.ShortChannelMapper;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.repository.ChannelSpecificationService;
import ru.clevertec.service.ChannelService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserMapper userMapper;
    private final ShortChannelMapper shortChannelMapper;
    private final ChannelMapper channelMapper;

    @Override
    public CreatedChannelDto saveChannel(CreateChannelDto channelDto) {
        ChannelEntity entity = channelMapper.toEntity(channelDto);
        ChannelEntity saved = channelRepository.save(entity);
        return channelMapper.channelToDto(saved);
    }

    @Override
    public Page<ChannelShortDto> searchChannel(Integer page, Integer size, ChannelFilter channelFilter) {
        Specification<ChannelEntity> specification = ChannelSpecificationService.createSpecification(channelFilter);

        return channelRepository.findAll(specification, PageRequest.of(page, size))
                .map(shortChannelMapper::toChannelShorDto);
    }

    @Override
    public CreatedChannelDto getChannel(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException(String.valueOf(channelId)));

        return channelMapper.channelToDto(channelEntity);
    }

    @Override
    @Transactional
    public UpdatedChannelDto updateChannel(Long channelId, ChannelUpdateDto channelDto) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException(String.valueOf(channelId)));

        ChannelEntity updatedChannel = channelMapper.updateChannel(channelEntity, channelDto);

        return channelMapper.toUpdatedDto(updatedChannel);
    }

    @Override
    public List<CreatedUserDto> getSubscribers(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException(String.valueOf(channelId)));

        Set<UserEntity> subscribers = channelEntity.getSubscribers();

        return userMapper.toDto(subscribers);
    }

    @Override
    @Transactional
    public UpdatedChannelDto patchUpdateChannel(Long channelId, ChannelUpdateDto channelDto) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException(String.valueOf(channelId)));

        ChannelEntity patchUpdated = channelMapper.patchUpdate(channelEntity, channelDto);

        return channelMapper.toUpdatedDto(patchUpdated);
    }

}
