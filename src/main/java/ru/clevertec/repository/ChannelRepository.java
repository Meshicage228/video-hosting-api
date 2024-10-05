package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.ChannelEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long>,
                                           JpaSpecificationExecutor<ChannelEntity>,
                                           PagingAndSortingRepository<ChannelEntity, Long> {

    @Query("SELECT c.title FROM ChannelEntity c JOIN c.subscribers s WHERE s.id = :subId")
    List<String> findChannelTitlesBySubscriber(@Param("subId") UUID subscriberId);
}

