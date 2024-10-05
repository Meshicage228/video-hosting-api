package ru.clevertec.service;

import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.dto.request.ChannelFilter;

import java.util.List;

public interface ChannelService {
    ChannelDto saveChannel(ChannelDto channelDto);
    List<PaginatedChannelDto> searchChannel(Integer page, Integer size, ChannelFilter channelId);
}
