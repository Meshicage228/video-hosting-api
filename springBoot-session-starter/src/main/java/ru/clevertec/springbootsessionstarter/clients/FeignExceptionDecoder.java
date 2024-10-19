package ru.clevertec.springbootsessionstarter.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.springbootsessionstarter.exception.BadRequestException;
import ru.clevertec.springbootsessionstarter.exception.ErrorMessage;
import feign.codec.ErrorDecoder;
import ru.clevertec.springbootsessionstarter.exception.InternalServerException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class FeignExceptionDecoder implements ErrorDecoder {

    private final static Map<Predicate<Integer>, Function<ErrorMessage, Exception>> errorHandlers = new HashMap<>(){{
        put(value -> value == 400, errorMessage -> {
            log.error("Exception message : {}; from service : {}", errorMessage.getMessage(), errorMessage.getServiceName());
            return new BadRequestException(errorMessage.getMessage());
        });
    }};

    @Override
    public Exception decode(String s, Response response) {
        try {
            byte[] arr = response.body().asInputStream().readAllBytes();
            var mapper = new ObjectMapper();
            ErrorMessage errorMessage = mapper.readValue(arr, ErrorMessage.class);

            return errorHandlers.entrySet().stream()
                    .filter(entry -> entry.getKey().test(response.status()))
                    .map(entry -> entry.getValue().apply(errorMessage))
                    .findFirst()
                    .orElseThrow(() -> {
                        String errorReason = errorMessage.getMessage();
                        log.error("No handler for status : {}; exception message : {}", response.status(), errorReason);
                        return new InternalServerException(errorReason);
                    });
        } catch (IOException e) {
            String readFailed = "Failed to read error message: " + e.getMessage();
            log.error(readFailed);
            return new InternalServerException(readFailed);
        }
    }
}
