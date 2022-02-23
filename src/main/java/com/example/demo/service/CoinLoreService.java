package com.example.demo.service;

import com.example.demo.controller.UserController;
import com.example.demo.dto.CryptoDto;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.Crypto;
import com.example.demo.repository.CryptoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Log
@Service
@AllArgsConstructor
public class CoinLoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinLoreService.class);
    private final RestTemplate restTemplate;
    private final CryptoRepository cryptoRepository;

    @Transactional
    public void updateCoin() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = "https://api.coinlore.net/api/ticker/{id}";

        List<Long> cryptoIds = cryptoRepository.getAllCryptoIds();
        List<Crypto> cryptoList = new ArrayList<>();
        for (Long id : cryptoIds) {
            Map<String, Long> params = new HashMap<>();
            params.put("id", id);

            ResponseEntity<CryptoDto> responseEntity = restTemplate.exchange(
                    url, HttpMethod.GET, entity, CryptoDto.class, params);

            if (Objects.nonNull(responseEntity.getBody())) {
                Crypto crypto = cryptoRepository.findById(id).orElseThrow();

                double changePercent = calculatePriceChangePercent(crypto.getPrice(),
                        responseEntity.getBody().getPrice());
                if (changePercent > 1) {
                    for (AppUser user: crypto.getUsers()) {
                        notifyUsers(crypto, user);
                    }

                }

                if (!Objects.equals(crypto.getPrice(), responseEntity.getBody().getPrice())) {
                    crypto.setPrice(responseEntity.getBody().getPrice());
                    cryptoList.add(crypto);
                }
            }
        }

        if (!CollectionUtils.isEmpty(cryptoList)) {
            cryptoRepository.saveAll(cryptoList);
        }
    }

    private void notifyUsers(Crypto crypto, AppUser appUser) {
        LOGGER.warn(crypto.getId() + appUser.getUserName());
    }

    private double calculatePriceChangePercent(Long oldPrice, Long newPrice) {
        double changePercent = ((newPrice - oldPrice) / (double)oldPrice);
        return changePercent;
    }
}
