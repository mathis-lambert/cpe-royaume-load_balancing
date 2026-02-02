package com.cpe.royaume.service;

import com.cpe.royaume.domain.Quest;
import com.cpe.royaume.client.RoyalApiClient;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuestFlowService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestFlowService.class);

    private final RoyalApiClient royalService;
    private final QuestStorageService questStorageService;
    private final QuestExpiryService questExpiryService;

    public QuestFlowService(
            RoyalApiClient royalService,
            QuestStorageService questStorageService,
            QuestExpiryService questExpiryService
    ) {
        this.royalService = royalService;
        this.questStorageService = questStorageService;
        this.questExpiryService = questExpiryService;
    }

    public void fetchAndSaveQuests() {
        Quest quest = royalService.getQuests();

        if (quest == null) {
            LOGGER.info("No quest returned by RoyalApiClient");
            return;
        }

        questStorageService.save(quest);
    }

    public void scheduleQuests() {
        List<Quest> quests = questStorageService.findQuestsToLaunch();

        for (Quest quest : quests) {
            questExpiryService.schedule(quest);
        }
    }

    public void scheduleQuest(String questId) throws Exception {
        Quest quest = questStorageService.findById(questId);

        if (quest == null) {
            LOGGER.info("No quest found with id {}", questId);
            throw new Exception("Quest not found");
        }

        questExpiryService.schedule(quest);
    }

    public List<Quest> findAllQuests() {
        return questStorageService.findAll();
    }

    public List<Quest> findAllPendingQuests() {
        return questStorageService.findAllPendingQuests();
    }
}
