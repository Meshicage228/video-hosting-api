package ru.clevertec.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BlackListRepository extends JpaRepository<BlackListsEntity, Long> {

    @Query("SELECT e.blockedLogin FROM BlackListsEntity e")
    Set<String> getAllBlockedLogins();
}
