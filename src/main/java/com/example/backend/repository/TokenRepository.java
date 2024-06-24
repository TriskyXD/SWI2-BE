package com.example.backend.repository;

import com.example.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllByUser_IdAndExpiredFalseAndRevokedFalse(Long id);

    Optional<Token> findByToken(String token);

}