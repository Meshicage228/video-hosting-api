package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.response.PaginatedChannelDtoResponse;
import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.dto.response.ChannelDtoResponse;
import ru.clevertec.dto.update.ChannelUpdateDto;
import ru.clevertec.service.ChannelService;

import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto channelDto) {
        ChannelDto channel = channelService.saveChannel(channelDto);

        return ResponseEntity.status(CREATED)
                .body(channel);
    }

    @GetMapping
    public ResponseEntity<PaginatedChannelDtoResponse> searchChannel(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                     @RequestParam(value = "size", defaultValue = "5") Integer size,
                                                                     @RequestBody(required = false) ChannelFilter channelFilter) {
        return ResponseEntity.ok(channelService.searchChannel(page, size, channelFilter));
    }

    @GetMapping("/{channelId}")
    public ResponseEntity<ChannelDtoResponse> getChannel(@PathVariable Long channelId) {
        return ResponseEntity.ok(channelService.getChannel(channelId));
    }

    @PutMapping("/{channelId}")
    public ResponseEntity<ChannelDto> updateChannel(@PathVariable Long channelId,
                                                     @RequestBody ChannelUpdateDto channelUpdateDto) {
        return ResponseEntity.ok(channelService.updateChannel(channelId, channelUpdateDto));
    }

    @GetMapping("/{channelId}/subscribers")
    public ResponseEntity<Set<UserDto>> getSubscribers(@PathVariable Long channelId) {
        return ResponseEntity.ok(channelService.getSubscribers(channelId));
    }

    @PatchMapping("/{channelId}")
    public ResponseEntity<ChannelDtoResponse> patchUpdateUser(@PathVariable Long channelId, @RequestBody Map<Object, Object> patch) {
        return ResponseEntity.ok(channelService.patchUpdateChannel(channelId, patch));
    }
}
