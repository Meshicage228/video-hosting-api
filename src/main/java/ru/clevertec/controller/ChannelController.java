package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.channel.*;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.service.ChannelService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreatedChannelDto createChannel(@ModelAttribute CreateChannelDto channelDto) {
        return channelService.saveChannel(channelDto);
    }

    @PostMapping("/search")
    public Page<ChannelShortDto> searchChannel(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "5") Integer size,
                                               @RequestBody(required = false) ChannelFilter channelFilter) {
        return channelService.searchChannel(page, size, channelFilter);
    }

    @GetMapping("/{channelId}")
    public CreatedChannelDto getChannel(@PathVariable Long channelId) {
        return channelService.getChannel(channelId);
    }

    @PutMapping("/{channelId}")
    public UpdatedChannelDto updateChannel(@PathVariable Long channelId,
                                           @RequestBody ChannelUpdateDto channelUpdateDto) {
        return channelService.updateChannel(channelId, channelUpdateDto);
    }

    @GetMapping("/{channelId}/subscribers")
    public List<CreatedUserDto> getSubscribers(@PathVariable Long channelId) {
        return channelService.getSubscribers(channelId);
    }

    @PatchMapping("/{channelId}")
    public UpdatedChannelDto patchUpdateChannel(@PathVariable Long channelId, @RequestBody ChannelUpdateDto updateDto) {
        return channelService.patchUpdateChannel(channelId, updateDto);
    }
}
