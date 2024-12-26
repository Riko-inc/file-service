package org.example.fileservice.repositories;

import org.example.fileservice.domain.enities.TokenRedisEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenRedisEntity, String> {

    Optional<TokenRedisEntity> getByToken(String token);
    Optional<TokenRedisEntity> getByTokenAndUserId(String token, String userId);
    List<TokenRedisEntity> findAllByUserId(String userId);
    void deleteAllByUserId(String userId);
    void deleteByToken(String token);
    void saveToken(TokenRedisEntity tokenRedisEntity);
}