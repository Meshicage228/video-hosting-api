package ru.clevertec.sessionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.clevertec.sessionservice.service.SchedulerLog;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import ru.clevertec.sessionservice.repository.SessionRepository;

//@EnableMongoRepositories(basePackageClasses = SessionRepository.class)
@SpringBootApplication
@EnableScheduling
public class SessionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionServiceApplication.class, args);
    }

}
