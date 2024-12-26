package org.example.fileservice.services;

import org.example.fileservice.domain.dto.requests.DocumentUpdateRequest;
import org.example.fileservice.domain.enities.DocumentEntity;
import org.example.fileservice.domain.enities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DocumentService {
    DocumentEntity createDocument(DocumentEntity document, UserEntity user);
    DocumentEntity updateDocument(DocumentUpdateRequest documentUpdateRequest);
    Optional<DocumentEntity> getDocumentById(Long id);
    List<DocumentEntity> getDocumentsByTitleContaining(String titleSubstring);
    List<DocumentEntity> getAllDocumentsByUser(UserEntity user);
    void deleteDocumentById(Long id);
}
