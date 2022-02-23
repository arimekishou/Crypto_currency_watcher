package com.example.demo;

import com.example.demo.service.CoinLoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final CoinLoreService coinLoreService;

    @Scheduled(fixedDelay = 60000)
    public void updateCrypto() {
        coinLoreService.updateCoin();
    }
}
