package ru.clevertec.service;

import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.response.PaginatedChannelDtoResponse;
import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.dto.response.ChannelDtoResponse;
import ru.clevertec.dto.update.ChannelUpdateDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChannelService {
    ChannelDto saveChannel(ChannelDto channelDto);

    List<PaginatedChannelDtoResponse> searchChannel(Integer page, Integer size, ChannelFilter channelId);

    ChannelDtoResponse getChannel(Long channelId);

    ChannelDto updateChannel(Long channelId, ChannelUpdateDto channelDto);

    Set<UserDto> getSubscribers(Long channelId);

    ChannelDtoResponse patchUpdateChannel(Long channelId, Map<Object, Object> patch);
}
