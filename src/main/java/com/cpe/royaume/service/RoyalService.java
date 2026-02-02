package com.cpe.royaume.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class RoyalService {

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

    public String getQuests() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/quests")
                        .queryParam("group", group)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public ApiResponse resolveQuest(final String questId) {
        String url = ROYAUME_API_URL + "/quests/" + questId + "/resolve";
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity(
                url,
                null,
                ApiResponse.class
        );
        return response.getBody();
    }
}