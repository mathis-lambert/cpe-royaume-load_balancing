package com.cpe.royaume.scheduler;

import com.cpe.royaume.service.QuestManagerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuestAutoScheduler {
    private final QuestManagerService questManagerService;

    public QuestAutoScheduler(QuestManagerService questManagerService) {
        this.questManagerService = questManagerService;
    }

    @Scheduled(fixedDelayString = "${royaume.auto.fetch:30000}")
    public void run() {
        questManagerService.fetchAndSaveQuests();
    }
}
