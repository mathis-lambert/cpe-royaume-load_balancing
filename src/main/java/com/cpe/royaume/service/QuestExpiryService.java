package com.cpe.royaume.service;

import com.cpe.royaume.client.RoyalApiClient;
import com.cpe.royaume.domain.Quest;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class QuestExpiryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestExpiryService.class);

    private final TaskScheduler taskScheduler;
    private final RoyalApiClient royalService;

    public QuestExpiryService(TaskScheduler taskScheduler, RoyalApiClient royalService) {
        this.taskScheduler = taskScheduler;
        this.royalService = royalService;
    }

    public void schedule(Quest quest) {
        if (quest == null || quest.getDelaiLimite() == null || quest.getId() == null) {
            return;
        }

        Instant deadline = quest.getDelaiLimite();
        Instant now = Instant.now();
        Instant startTime = deadline.isAfter(now) ? deadline : now;
        String questId = quest.getId();

        taskScheduler.schedule(() -> resolveQuest(questId), startTime);
    }

    private void resolveQuest(String questId) {
        try {
            LOGGER.info("Quest {} has expired", questId);
            royalService.resolveQuest(questId);
        } catch (Exception e) {
            LOGGER.error("Error while resolving quest {}", questId, e);
        }
    }
}
