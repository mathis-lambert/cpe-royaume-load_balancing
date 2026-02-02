package com.cpe.royaume.controller;

import com.cpe.royaume.domain.Quest;
import com.cpe.royaume.service.QuestManagerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/royaume")
public class QuestController {
    private final QuestManagerService questManagerService;

    public QuestController(QuestManagerService questManagerService) {
        this.questManagerService = questManagerService;
    }

    @GetMapping("/quests")
    public List<Quest> getQuests() {
        return questManagerService.findAllPendingQuests();
    }

    @PostMapping("/quests/{id}/launch")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> launchQuest(@PathVariable String id) throws Exception {
        questManagerService.scheduleQuest(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
