package com.cpe.royaume.scheduler;

import com.cpe.royaume.service.QuestManagerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "royaume.mode", havingValue = "auto")
public class LaunchQuestsScheduler {
    private final QuestManagerService questManagerService;

    public LaunchQuestsScheduler(QuestManagerService questManagerService) {
        this.questManagerService = questManagerService;
    }

    @Scheduled(fixedDelayString = "${royaume.auto.resolve:10000}")
    public void run() {
        questManagerService.scheduleQuests();
    }
}
