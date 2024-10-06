package ru.clevertec.service;

import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.dto.update.ChannelUpdateDto;

import java.util.List;

public interface ChannelService {
    ChannelDto saveChannel(ChannelDto channelDto);

    List<PaginatedChannelDto> searchChannel(Integer page, Integer size, ChannelFilter channelId);

    ChannelDto getChannel(Long channelId);

    ChannelDto updateChannel(Long channelId, ChannelUpdateDto channelDto);
}
