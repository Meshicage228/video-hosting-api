package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.channel.ChannelShortDto;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.entity.UserEntity;

import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class
)
public interface ShortChannelMapper {

    @Mapping(target = "countOfSubscribers", source = "subscribers", qualifiedByName = "getCountSubscribers")
    ChannelShortDto toChannelShorDto(ChannelEntity channelEntity);

    @Named("getCountSubscribers")
    default Integer countSubscribers(Set<UserEntity> userEntities) {
        return userEntities.size();
    }
}
