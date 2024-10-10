package ru.clevertec.dto.response;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaginatedChannelDtoResponse {
    private List<ChannelShortDto> channelList;
    private Pageable pageable;
}
