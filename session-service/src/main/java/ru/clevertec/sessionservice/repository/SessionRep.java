package ru.clevertec.sessionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.sessionservice.entity.Session;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionRep extends JpaRepository<Session, Long> {
    Optional<Session> findByLogin(String login);
    void deleteByCreationDateBefore(LocalDate date);
}
