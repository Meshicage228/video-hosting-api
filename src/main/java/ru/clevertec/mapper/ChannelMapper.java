package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.entity.ChannelEntity;
import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = UserMapper.class
)
public interface ChannelMapper {

    ChannelDto toDto(ChannelEntity entity);

    ChannelEntity toEntity(ChannelDto dto);

    @AfterMapping
    default void afterToEntityMapping(@MappingTarget ChannelEntity entity) {
        entity.setDateOfCreation(LocalDateTime.now());
    }

    @Mapping(target = "countOfSubscribers", expression = "java(channelEntity.getSubscribers().size())")
    PaginatedChannelDto paginatedSearchResult(ChannelEntity channelEntity);
}
