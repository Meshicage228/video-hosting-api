package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.response.PaginatedChannelDtoResponse;
import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.dto.response.ChannelDtoResponse;
import ru.clevertec.dto.update.ChannelUpdateDto;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.mapper.ChannelMapper;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.service.ChannelService;
import ru.clevertec.service.SpecificationService;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserMapper userMapper;
    private final ChannelMapper mapper;
    private final FieldSetterService fieldSetterService;
    private final SpecificationService specificationService;

    @Override
    public ChannelDto saveChannel(ChannelDto channelDto) {
        ChannelEntity entity = mapper.toEntity(channelDto);
        ChannelEntity saved = channelRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public List<PaginatedChannelDtoResponse> searchChannel(Integer page, Integer size, ChannelFilter channelFilter) {
        Specification<ChannelEntity> specification = specificationService.createSpecification(channelFilter);

        return channelRepository.findAll(specification, PageRequest.of(page, size))
                .stream()
                .map(mapper::paginatedSearchResult)
                .toList();
    }

    @Override
    public ChannelDtoResponse getChannel(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(ChannelNotFoundException::new);

        return mapper.channelToDo(channelEntity);
    }

    @Override
    @Transactional
    public ChannelDto updateChannel(Long channelId, ChannelUpdateDto channelDto) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(ChannelNotFoundException::new);

        ChannelEntity updatedChannel = mapper.updateChannel(channelEntity, channelDto);

        return mapper.toDto(updatedChannel);
    }

    @Override
    public Set<UserDto> getSubscribers(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(ChannelNotFoundException::new);

        Set<UserEntity> subscribers = channelEntity.getSubscribers();

        return userMapper.getUserDtos(subscribers);
    }

    @Override
    @Transactional
    public ChannelDtoResponse patchUpdateChannel(Long channelId, Map<Object, Object> patch) {
        ChannelEntity channelEntity = channelRepository.findById(channelId)
                .orElseThrow(ChannelNotFoundException::new);

        fieldSetterService.setFields(channelEntity, patch);

        return mapper.channelToDo(channelEntity);
    }

}
