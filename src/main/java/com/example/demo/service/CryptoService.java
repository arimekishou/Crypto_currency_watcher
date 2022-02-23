package com.example.demo.service;

import com.example.demo.entities.Crypto;

public interface CryptoService {

    Crypto getById(Long id);

    Crypto getBySymbol(String symbol);
}
