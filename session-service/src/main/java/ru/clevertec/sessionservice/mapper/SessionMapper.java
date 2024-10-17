package ru.clevertec.sessionservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.sessionservice.dto.SessionRequest;
import ru.clevertec.sessionservice.dto.SessionResponse;
import ru.clevertec.sessionservice.entity.Session;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SessionMapper {

    SessionResponse fromSessionToResponse(Session session);

    @Mapping(target = "creationDate", expression = "java(java.time.LocalDate.now())")
    Session fromRequestToSession(SessionRequest session);
}
