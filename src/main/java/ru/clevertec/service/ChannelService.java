package ru.clevertec.service;

import org.springframework.data.domain.Page;
import ru.clevertec.dto.channel.*;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.filter.ChannelFilter;

import java.util.List;

public interface ChannelService {
    CreatedChannelDto saveChannel(CreateChannelDto channelDto);

    Page<ChannelShortDto> searchChannel(Integer page, Integer size, ChannelFilter channelId);

    CreatedChannelDto getChannel(Long channelId);

    UpdatedChannelDto updateChannel(Long channelId, ChannelUpdateDto channelDto);

    List<CreatedUserDto> getSubscribers(Long channelId);

    UpdatedChannelDto patchUpdateChannel(Long channelId, ChannelUpdateDto channelDto);
}
