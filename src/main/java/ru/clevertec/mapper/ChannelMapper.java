package ru.clevertec.mapper;

import org.mapstruct.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.request.ChannelDtoRequest;
import ru.clevertec.dto.response.PaginatedChannelDtoResponse;
import ru.clevertec.dto.response.ChannelDtoResponse;
import ru.clevertec.dto.response.SubscriptionDtoResponse;
import ru.clevertec.dto.update.ChannelUpdateDto;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.exception.FileReadFailedException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryMapper.class, ShortChannelMapper.class}
)
public interface ChannelMapper {

    @Mapping(target = "avatar", expression = "java(convertBytesToString(entity.getAvatar()))")
    ChannelDtoResponse toDto(ChannelEntity entity);

    @Mapping(target = "avatar", expression = "java(convertFileToBytes(dto.getAvatar()))")
    ChannelEntity toEntity(ChannelDtoRequest dto);

    default String convertBytesToString(byte[] arr) {
        return Base64.getEncoder().encodeToString(arr);
    }

    default byte[] convertFileToBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new FileReadFailedException();
        }
    }

    @AfterMapping
    default void afterToEntityMapping(@MappingTarget ChannelEntity entity) {
        entity.setDateOfCreation(LocalDateTime.now());
    }

    PaginatedChannelDtoResponse channelEntitiesToPaginatedChannelDtoResponse(List<ChannelEntity> channelList, Pageable pageable);

    @Mapping(target = "countOfSubscribers", expression = "java(channelEntity.getSubscribers().size())")
    ChannelDtoResponse channelToDto(ChannelEntity channelEntity);

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
