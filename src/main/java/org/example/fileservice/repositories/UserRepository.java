package org.example.fileservice.repositories;

import jakarta.transaction.Transactional;
import org.example.fileservice.domain.enities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteUserEntityByEmail(String email);
}