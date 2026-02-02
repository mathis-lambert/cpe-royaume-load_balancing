package com.cpe.royaume.service;

import com.cpe.royaume.entity.ApiResponse;
import com.cpe.royaume.entity.Quest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RoyalService {
    private final Logger LOGGER = LoggerFactory.getLogger(RoyalService.class);

    private final WebClient webClient;
    private final String group;

    public RoyalService(
            @Value("${royal.api.base-url}") String baseUrl,
            @Value("${royal.group}") String group
    ) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.group = group;
    }

    public Quest getQuests() {
        ApiResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/quests")
                        .queryParam("group", group)
                        .build())
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();
        LOGGER.debug("Response from getQuests: {}", response);
        return response != null ? response.getQuest() : null;
    }

    public ApiResponse resolveQuest(final String questId) {
        ApiResponse response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/quests/{id}/resolve")
                        .queryParam("group", group)
                        .build(questId))
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();
        LOGGER.debug("Response from resolveQuest: {}", response);
        return response;
    }
}