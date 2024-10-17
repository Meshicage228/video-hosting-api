package ru.clevertec.springbootsessionstarter.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "service-feign.clients")
public class FeignPropertiesProvider {
    private String path;
    private String name;
    private String url;
}
