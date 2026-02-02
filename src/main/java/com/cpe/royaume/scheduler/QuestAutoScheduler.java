package com.cpe.royaume.scheduler;

import com.cpe.royaume.service.QuestFlowService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "royaume.mode", havingValue = "auto")
public class QuestAutoScheduler {
    private final QuestFlowService questFlowService;

    public QuestAutoScheduler(QuestFlowService questFlowService) {
        this.questFlowService = questFlowService;
    }

    @Scheduled(fixedDelayString = "${royaume.auto.period-ms:30000}")
    public void run() {
        questFlowService.runOnce();
    }
}
