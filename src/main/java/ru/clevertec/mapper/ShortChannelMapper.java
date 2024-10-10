package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.response.ChannelShortDto;
import ru.clevertec.entity.ChannelEntity;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class
)
public interface ShortChannelMapper {

    @Mapping(target = "countOfSubscribers", expression = "java(channelEntity.getSubscribers().size())")
    ChannelShortDto toChannelShorDto(ChannelEntity channelEntity);

    List<ChannelShortDto> toShortDtoList(List<ChannelEntity> channelEntityPage);
}
