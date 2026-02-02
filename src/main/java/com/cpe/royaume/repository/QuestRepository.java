package com.cpe.royaume.repository;

import com.cpe.royaume.domain.EnumStatus;
import com.cpe.royaume.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {
    List<Quest> findByStatus(EnumStatus status);
}
