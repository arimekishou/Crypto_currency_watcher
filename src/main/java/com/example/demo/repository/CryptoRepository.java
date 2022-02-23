package com.example.demo.repository;


import com.example.demo.entities.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CryptoRepository extends JpaRepository<Crypto, Long>, JpaSpecificationExecutor<Crypto> {

    @Query("SELECT c.id from Crypto c")
    List<Long> getAllCryptoIds();

    Optional<Crypto> findById(Long id);

    Crypto findBySymbol(String symbol);

}