package com.cpe.royaume.controller;

import com.cpe.royaume.entity.Quest;
import com.cpe.royaume.service.RoyalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/royaume")
public class RoyaumeController {
    private final RoyalService royalService;

    public RoyaumeController(RoyalService royalService) {
        this.royalService = royalService;
    }

    @GetMapping("/quests")
    public Quest getQuests() {
        return royalService.getQuests();
    }
}
