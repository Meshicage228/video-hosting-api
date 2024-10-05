package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.dto.request.ChannelFilter;
import ru.clevertec.service.ChannelService;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    private ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto channelDto){
        ChannelDto channel = channelService.saveChannel(channelDto);

        return ResponseEntity.status(CREATED)
                .body(channel);
    }

    @GetMapping
    private ResponseEntity<List<PaginatedChannelDto>> searchChannel(@RequestParam("page") Integer page,
                                                                    @RequestParam("size") Integer size,
                                                                    @RequestBody ChannelFilter channelFilter){
        return ResponseEntity.ok(channelService.searchChannel(page, size, channelFilter));
    }
}
