package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.entity.ChannelEntity;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ChannelMapper {

    ChannelDto toDto(ChannelEntity entity);

    ChannelEntity toEntity(ChannelDto dto);
}
