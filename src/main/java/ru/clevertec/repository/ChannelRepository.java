package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.ChannelEntity;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

}
