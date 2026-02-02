package com.cpe.royaume.controller;

import com.cpe.royaume.domain.Quest;
import com.cpe.royaume.service.QuestFlowService;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/royaume")
public class QuestController {
    private final QuestFlowService questFlowService;

    public QuestController(QuestFlowService questFlowService) {
        this.questFlowService = questFlowService;
    }

    @GetMapping("/quests")
    public List<Quest> getQuests() {
        return questFlowService.runOnce();
    }
}
