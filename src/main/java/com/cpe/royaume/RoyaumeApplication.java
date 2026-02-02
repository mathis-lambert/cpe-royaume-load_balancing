package com.cpe.royaume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoyaumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoyaumeApplication.class, args);
    }

}
