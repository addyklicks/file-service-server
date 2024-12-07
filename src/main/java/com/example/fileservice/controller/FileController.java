package com.example.fileservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController implements InitializingBean {

    @Value("${celonis.api-key}")
    private String apiKey;

    @Value("${file.storage.path}")
    private String storagePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        File directory = new File(storagePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @PostMapping("/files")
    public ResponseEntity<String> uploadFile(
            @RequestHeader("Celonis-Auth") String authHeader,
            @RequestParam("file") MultipartFile file) {

        if (!apiKey.equals(authHeader)) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        try {
            Path filePath = Paths.get(storagePath, file.getOriginalFilename());
            file.transferTo(filePath);
            return new ResponseEntity<>("File uploaded successfully: " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<byte[]> downloadFile(
            @PathVariable String filename) {

        try {
            Path filePath = Paths.get(storagePath, filename);
            byte[] fileContent = Files.readAllBytes(filePath);
            return new ResponseEntity<>(fileContent, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public String home() {
        return "Welcome to the File Service Application! Use /files to upload or download files.";
    }
}