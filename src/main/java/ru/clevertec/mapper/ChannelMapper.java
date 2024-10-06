package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.dto.update.ChannelUpdateDto;
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

    @BeanMapping(ignoreByDefault = true)
    @Mappings(value = {
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "shortDescription", source = "shortDescription"),
            @Mapping(target = "language", source = "language"),
            @Mapping(target = "category", source = "category")
    })
    ChannelEntity updateChannel(@MappingTarget ChannelEntity target, ChannelUpdateDto channelDto);
}
