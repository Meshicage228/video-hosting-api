package ru.clevertec.sessionservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.sessionservice.entity.Session;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {
    Optional<Session> findByLogin(String login);
    void deleteByCreationDateBefore(LocalDate date);
}
