package com.cpe.royaume.controller;

import com.cpe.royaume.entity.Quest;
import com.cpe.royaume.service.QuestExpiryService;
import com.cpe.royaume.service.QuestStorageService;
import com.cpe.royaume.service.RoyalService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/royaume")
public class RoyaumeController {
    private final Logger LOGGER = LoggerFactory.getLogger(RoyaumeController.class);
    private final RoyalService royalService;
    private final QuestStorageService questStorageService;
    private final QuestExpiryService questExpiryService;

    public RoyaumeController(
            RoyalService royalService,
            QuestStorageService questStorageService,
            QuestExpiryService questExpiryService
    ) {
        this.royalService = royalService;
        this.questStorageService = questStorageService;
        this.questExpiryService = questExpiryService;
    }

    @GetMapping("/quests")
    public List<Quest> getQuests() {
        Quest quest = royalService.getQuests();

        if (quest == null) {
            LOGGER.info("No quest returned by RoyalService");
            return questStorageService.findAll();
        }

        questStorageService.save(quest);
        questExpiryService.schedule(quest);

        return questStorageService.findAll();
    }
}
