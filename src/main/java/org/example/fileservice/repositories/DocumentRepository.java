package org.example.fileservice.repositories;

import jakarta.transaction.Transactional;
import org.example.fileservice.domain.enities.DocumentEntity;
import org.example.fileservice.domain.enities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> getAllByCreatedBy(UserEntity createdBy);
    List<DocumentEntity> getByTitleContainingIgnoreCase(String title);
}
