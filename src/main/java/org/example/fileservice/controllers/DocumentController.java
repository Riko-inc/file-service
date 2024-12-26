package org.example.fileservice.controllers;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.fileservice.domain.enities.DocumentEntity;
import org.example.fileservice.domain.enities.UserEntity;
import org.example.fileservice.services.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("api/v1/documents")
@RequiredArgsConstructor
@Validated
@Tag(name = "Работа с документами")
@Slf4j
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public String listDocuments(Principal principal, Model model) {
        List<DocumentEntity> documents = documentService.getAllDocumentsByUser((UserEntity) principal);
        model.addAttribute("documents", documents);
        return "document_list";
    }

    @GetMapping("/{id}")
    public String editDocument(@PathVariable Long id, Model model) {
        Optional<DocumentEntity> documentOptional = documentService.getDocumentById(id);
        if (documentOptional.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("document", documentOptional.get());
        return "document_editor";
    }

    @GetMapping("/new")
    public String createDocument(Principal principal) {
        int number = documentService.getDocumentsByTitleContaining("Untitled Document").size();
        DocumentEntity newDocument = DocumentEntity.builder()
                .createdAt(LocalDateTime.now())
                .content("")
                .title("Untitled Document" + (number == 0 ? "": "(" + number + ")"))
                .build();
        documentService.createDocument(newDocument, (UserEntity) principal);
        return "redirect:/api/v1/documents/" + newDocument.getDocumentId();
    }

    @PostMapping("/{id}")
    public String saveDocument(Principal principal, @PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        Optional<DocumentEntity> documentOptional = documentService.getDocumentById(id);
        if (documentOptional.isPresent()) {
            DocumentEntity document = documentOptional.get();
            document.setTitle(title);
            document.setContent(content);
            document.setEditedBy((UserEntity) principal);
            document.setEditedAt(LocalDateTime.now());
            documentService.createDocument(document, (UserEntity) principal);
        }
        return "redirect:/api/v1/documents";
    }

    @PostMapping("/{id}/delete")
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteDocumentById(id);
        return "redirect:/api/v1/documents";
    }
//
//    @PostMapping("/upload_image")
//    @ResponseBody
//    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        String uploadDir = "uploads/images/";
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(uploadDir, fileName);
//        Files.createDirectories(filePath.getParent());
//        Files.write(filePath, file.getBytes());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("link", "/uploads/images/" + fileName);
//        return response;
//    }
//
//    @PostMapping("/upload_file")
//    @ResponseBody
//    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        String uploadDir = "uploads/files/";
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(uploadDir, fileName);
//        Files.createDirectories(filePath.getParent());
//        Files.write(filePath, file.getBytes());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("link", "/uploads/files/" + fileName);
//        return response;
//    }


    //    @Operation(summary = "Скачать файл")
//    @PostMapping(value = "/download", produces = { MediaType.MULTIPART_FORM_DATA_VALUE })
//    @SneakyThrows
//    @SecurityRequirement(name = "JWT")
//    public ResponseEntity<Resource> createDocument(@RequestBody CreateFileRequest request){
//        Resource fileResource = new FileSystemResource("Insert_here_some_path");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + fileResource.getFilename() + "\"");
//        return new ResponseEntity<>(fileResource, headers, HttpStatus.OK);
//    }


//    @Operation(summary = "Загрузить файл")
//    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//    @SecurityRequirement(name = "JWT")
//    public ResponseEntity<> createTemplateAndGetFields(@RequestParam(value = "name", required = false) String templateName, @RequestParam(name="file") @NotNull MultipartFile file) throws IOException {
//        log.debug("Got file: {}", file.getOriginalFilename());
//        log.debug("Got templateName: {}", templateName);
//        return ResponseEntity.ok(templateService.createTemplateResponse(templateName, file));
//    }
}
