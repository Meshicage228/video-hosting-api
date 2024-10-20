package ru.clevertec.mapper;

import lombok.SneakyThrows;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.channel.*;
import ru.clevertec.dto.subscription.SubscriptionDto;
import ru.clevertec.entity.ChannelEntity;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryMapper.class, ShortChannelMapper.class}
)
public interface ChannelMapper {

    @Mapping(target = "avatar", source = "avatar", qualifiedByName = "getStringAvatar")
    UpdatedChannelDto toUpdatedDto(ChannelEntity entity);

    @Named("getStringAvatar")
    default String getConvertedAvatar(byte[] avatar) {
        return Base64.getEncoder().encodeToString(avatar);
    }

    @Mapping(target = "avatar", expression = "java(convertFileToBytes(dto.getAvatar()))")
    ChannelEntity toEntity(CreateChannelDto dto);

    @SneakyThrows
    default byte[] convertFileToBytes(MultipartFile file) {
        return file.getBytes();
    }

    @AfterMapping
    default void afterToEntityMapping(@MappingTarget ChannelEntity entity) {
        entity.setDateOfCreation(LocalDateTime.now());
    }

    @Mapping(target = "countOfSubscribers", source = "subscribers", qualifiedByName = "getCountSubscribers")
    @Mapping(target = "avatar", source = "avatar", qualifiedByName = "getStringAvatar")
    CreatedChannelDto channelToDto(ChannelEntity channelEntity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "shortDescription", source = "shortDescription")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "category", source = "category")
    ChannelEntity updateChannel(@MappingTarget ChannelEntity target, ChannelUpdateDto channelDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ChannelEntity patchUpdate(@MappingTarget ChannelEntity target, ChannelUpdateDto channelDto);

    Set<SubscriptionDto> toSubscriptionDtos(Set<ChannelEntity> subscriptionEntities);
}
