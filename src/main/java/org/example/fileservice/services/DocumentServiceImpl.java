package org.example.fileservice.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.example.fileservice.domain.dto.requests.DocumentUpdateRequest;
import org.example.fileservice.domain.enities.DocumentEntity;
import org.example.fileservice.domain.enities.UserEntity;
import org.example.fileservice.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Override
    public DocumentEntity createDocument(DocumentEntity document, UserEntity user) {
        document.setCreatedBy(user);
        document.setEditedBy(user);
        return documentRepository.save(document);
    }

    @Override
    public DocumentEntity updateDocument(DocumentUpdateRequest documentUpdateRequest) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<DocumentEntity> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    @Override
    public List<DocumentEntity> getDocumentsByTitleContaining(String titleSubstring) {
        return documentRepository.getByTitleContainingIgnoreCase(titleSubstring);
    }

    @Override
    public List<DocumentEntity> getAllDocumentsByUser(UserEntity user) {
        return documentRepository.getAllByCreatedBy(user);
    }

    @Override
    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }
}
