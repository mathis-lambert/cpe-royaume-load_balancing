package com.cpe.royaume.repository;

import com.cpe.royaume.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {
}
