package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.dto.update.ChannelUpdateDto;
import ru.clevertec.service.ChannelService;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    private ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto channelDto) {
        ChannelDto channel = channelService.saveChannel(channelDto);

        return ResponseEntity.status(CREATED)
                .body(channel);
    }

    @GetMapping
    private ResponseEntity<List<PaginatedChannelDto>> searchChannel(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(value = "size", defaultValue = "5") Integer size,
                                                                    @RequestBody(required = false) ChannelFilter channelFilter) {
        return ResponseEntity.ok(channelService.searchChannel(page, size, channelFilter));
    }

    @GetMapping("/{channelId}")
    private ResponseEntity<ChannelDto> getChannel(@PathVariable(value = "channelId") Long channelId) {
        return ResponseEntity.ok(channelService.getChannel(channelId));
    }

    @PutMapping("/{channelId}")
    private ResponseEntity<ChannelDto> updateChannel(@PathVariable("channelId") Long channelId,
                                                     @RequestBody ChannelUpdateDto channelUpdateDto) {
        return ResponseEntity.ok(channelService.updateChannel(channelId, channelUpdateDto));
    }
}
