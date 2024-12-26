package org.example.fileservice.controllers;

import jakarta.persistence.Column;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FilesController {

    @PostMapping("/upload_image")
    @ResponseBody
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/images/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        Map<String, String> response = new HashMap<>();
        response.put("link", "/uploads/images/" + fileName);
        return response;
    }

    @PostMapping("/upload_file")
    @ResponseBody
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/files/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        Map<String, String> response = new HashMap<>();
        response.put("link", "/uploads/files/" + fileName);
        return response;
    }
}
