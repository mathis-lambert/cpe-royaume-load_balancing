package com.cpe.royaume.repository;

import com.cpe.royaume.domain.EnumStatus;
import com.cpe.royaume.domain.Quest;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {
    List<Quest> findByStatus(EnumStatus status);
}
