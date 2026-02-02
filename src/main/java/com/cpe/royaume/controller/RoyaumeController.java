package com.cpe.royaume.controller;

import com.cpe.royaume.entity.ApiResponse;
import com.cpe.royaume.entity.Quest;
import com.cpe.royaume.repository.QuestRepository;
import com.cpe.royaume.service.RoyalService;
import java.time.Instant;
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
    private final QuestRepository questRepository;

    public RoyaumeController(RoyalService royalService, QuestRepository questRepository) {
        this.royalService = royalService;
        this.questRepository = questRepository;
    }

    @GetMapping("/quests")
    public ApiResponse getQuests() {
        ApiResponse response = royalService.getQuests();

        if (response == null) {
            LOGGER.info("No response from RoyalService");
            return null;
        }

        Quest quest = response.getQuest();

        if (quest == null) {
            LOGGER.info("No quest available");
            return null;
        }

        questRepository.save(quest);

        this.resolveQuest(quest);

        return response;
    }

    private void resolveQuest(Quest quest) {
        Instant currentDate = Instant.now();
        // calculate delta in seconds
        if (quest.getDelaiLimite() != null) {
            float delta = (quest.getDelaiLimite().toEpochMilli() - currentDate.toEpochMilli()) / 1000f;
            waitForDelai(quest, delta);
        }
    }

    private void waitForDelai(Quest quest, float delta) {
        // asynchronous sleeping for delta seconds
        Thread.ofVirtual().start(() -> {
            try {
                Thread.sleep((long) (delta * 1000));
                LOGGER.info("Quest {} has expired", quest.getId());
                ApiResponse resolveQuest = royalService.resolveQuest(quest.getId());
                LOGGER.info("Quest {} resolved with response: {}", quest.getId(), resolveQuest);
            } catch (InterruptedException e) {
                LOGGER.error("Error while waiting for quest to expire", e);
                Thread.currentThread().interrupt();
            }
        });
    }
}
