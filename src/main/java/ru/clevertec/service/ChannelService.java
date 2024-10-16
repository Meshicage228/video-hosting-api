package ru.clevertec.service;

import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.request.ChannelDtoRequest;
import ru.clevertec.dto.response.PaginatedChannelDtoResponse;
import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.dto.response.ChannelDtoResponse;
import ru.clevertec.dto.update.ChannelUpdateDto;

import java.util.Map;
import java.util.Set;

public interface ChannelService {
    ChannelDtoResponse saveChannel(ChannelDtoRequest channelDto);

    PaginatedChannelDtoResponse searchChannel(Integer page, Integer size, ChannelFilter channelId);

    ChannelDtoResponse getChannel(Long channelId);

    ChannelDtoResponse updateChannel(Long channelId, ChannelUpdateDto channelDto);

    Set<UserDto> getSubscribers(Long channelId);

    ChannelDtoResponse patchUpdateChannel(Long channelId, Map<Object, Object> patch);
}
