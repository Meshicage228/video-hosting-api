package ru.clevertec.sessionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.clevertec.sessionservice.repository.SessionRepository;

@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = SessionRepository.class)
public class SessionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionServiceApplication.class, args);
    }

}
