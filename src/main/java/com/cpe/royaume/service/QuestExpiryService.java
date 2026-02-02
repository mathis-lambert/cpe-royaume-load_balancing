package com.cpe.royaume.service;

import com.cpe.royaume.client.RoyalApiClient;
import com.cpe.royaume.domain.ApiResponse;
import com.cpe.royaume.domain.EnumStatus;
import com.cpe.royaume.domain.Quest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class QuestExpiryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestExpiryService.class);

    private final TaskScheduler taskScheduler;
    private final RoyalApiClient royalService;
    private final QuestStorageService questStorageService;

    public QuestExpiryService(
            TaskScheduler taskScheduler,
            RoyalApiClient royalService,
            QuestStorageService questStorageService) {
        this.taskScheduler = taskScheduler;
        this.royalService = royalService;
        this.questStorageService = questStorageService;
    }

    private boolean isQuestExpired(Quest quest) {
        LOGGER.info("Checking if quest {} is expired, Checking deadline {}", quest.getId(), quest.getDelaiLimite());
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(quest.getDelaiLimite());
    }

    public void schedule(Quest quest) {
        if (quest == null || quest.getDelaiLimite() == null || quest.getId() == null) {
            return;
        }

        if (isQuestExpired(quest)) {
            questStorageService.setQuestStatus(quest.getId(), EnumStatus.EXPIRED);
            LOGGER.info("Quest {} is already expired", quest.getId());
            return;
        }

        LocalDateTime deadline = quest.getDelaiLimite();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = deadline.isAfter(now) ? deadline : now;

        String questId = quest.getId();

        LOGGER.info("Scheduling quest {} to be resolved at {}", questId, startTime);

        taskScheduler.schedule(
                () -> resolveQuest(questId),
                startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private void resolveQuest(String questId) {
        try {
            LOGGER.info("Quest {} has expired", questId);
            ApiResponse resolvedQuest = royalService.resolveQuest(questId);
            if (resolvedQuest != null) {
                this.questStorageService.setQuestStatus(questId, EnumStatus.RESOLVED);
                LOGGER.info("Quest {} resolved with response: {}", questId, resolvedQuest);
            }
        } catch (Exception e) {
            LOGGER.error("Error while resolving quest {}", questId, e);
        }
    }
}
