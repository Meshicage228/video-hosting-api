package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.response.PaginatedChannelDtoResponse;
import ru.clevertec.dto.response.ChannelDtoResponse;
import ru.clevertec.dto.response.SubscriptionDtoResponse;
import ru.clevertec.dto.update.ChannelUpdateDto;
import ru.clevertec.entity.ChannelEntity;

import java.time.LocalDateTime;
import java.util.Set;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class
)
public interface ChannelMapper {

    ChannelDto toDto(ChannelEntity entity);

    ChannelEntity toEntity(ChannelDto dto);

    @AfterMapping
    default void afterToEntityMapping(@MappingTarget ChannelEntity entity) {
        entity.setDateOfCreation(LocalDateTime.now());
    }

    @Mapping(target = "countOfSubscribers", expression = "java(channelEntity.getSubscribers().size())")
    PaginatedChannelDtoResponse paginatedSearchResult(ChannelEntity channelEntity);

    @Mapping(target = "countOfSubscribers", expression = "java(channelEntity.getSubscribers().size())")
    ChannelDtoResponse channelToDo(ChannelEntity channelEntity);

    @BeanMapping(ignoreByDefault = true)
    @Mappings(value = {
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "shortDescription", source = "shortDescription"),
            @Mapping(target = "language", source = "language"),
            @Mapping(target = "category", source = "category")
    })
    ChannelEntity updateChannel(@MappingTarget ChannelEntity target, ChannelUpdateDto channelDto);

    Set<SubscriptionDtoResponse> toSubscriptionDtos(Set<ChannelEntity> subscriptionEntities);
}
