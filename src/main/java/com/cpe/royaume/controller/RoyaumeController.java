package com.cpe.royaume.controller;

import com.cpe.royaume.entity.ApiResponse;
import com.cpe.royaume.entity.Quest;
import com.cpe.royaume.service.RoyalService;

import java.sql.Date;

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

    public RoyaumeController(RoyalService royalService) {
        this.royalService = royalService;
    }

    @GetMapping("/quests")
    public ApiResponse getQuests() {
        ApiResponse response = royalService.getQuests();
        Quest quest = response.getQuest();

        if (quest == null) {
            LOGGER.info("No quest available");
            return response;
        }

        


        Date currentDate = new Date(System.currentTimeMillis());

        // calculate delta in seconds
        float delta = (quest.getDelaiLimite().getTime() - currentDate.getTime()) / 1000f;

        // asynchronous sleeping for delta seconds
        new Thread(() -> {
            try {
                Thread.sleep((long) (delta * 1000));
                LOGGER.info("Quest {} has expired", quest.getId());
                royalService.resolveQuest(quest.getId());
            } catch (InterruptedException e) {
                LOGGER.error("Error while waiting for quest to expire", e);
            }
        }).start();

        return response;
    }
}
