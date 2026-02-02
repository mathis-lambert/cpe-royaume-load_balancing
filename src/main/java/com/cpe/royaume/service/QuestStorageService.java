package com.cpe.royaume.service;

import com.cpe.royaume.domain.EnumStatus;
import com.cpe.royaume.domain.Quest;
import com.cpe.royaume.repository.QuestRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuestStorageService {
    private final QuestRepository questRepository;

    public QuestStorageService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public void save(Quest quest) {
        if (quest == null) {
            return;
        }
        questRepository.save(quest);
    }

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    public List<Quest> findAllPendingQuests() {
        return questRepository.findByStatus(EnumStatus.PENDING);
    }

    public Quest findById(String questId) {
        return questRepository.findById(questId).orElse(null);
    }

    public void setQuestStatus(String questId, EnumStatus enumStatus) {
        Quest quest = findById(questId);
        if (quest != null) {
            quest.setStatus(enumStatus);
            save(quest);
        }
    }
}
