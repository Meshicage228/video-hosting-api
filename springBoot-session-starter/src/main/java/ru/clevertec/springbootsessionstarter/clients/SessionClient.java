package ru.clevertec.springbootsessionstarter.clients;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.clevertec.springbootsessionstarter.dto.Session;

@FeignClient(
        name = "${service-feign.clients.name}",
        url = "${service-feign.clients.url}",
        path = "${service-feign.clients.path}"
)
@ConditionalOnProperty(prefix = "session.provider", value = "enabled", havingValue = "true")
public interface SessionClient {
    @GetMapping
    Session getOrCreateAndGetSession(@RequestParam("login") String login);
}
