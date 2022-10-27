package com.acroriver.server.team.controller;

import com.acroriver.server.team.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
@Slf4j
public class AmazonS3Controller {

    private final AmazonS3Service amazonS3Service;

    @PostMapping("/file")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile multipartFile) {
        log.info(multipartFile.toString());
        return new ResponseEntity<>(amazonS3Service.uploadFile(multipartFile), HttpStatus.CREATED);
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestParam String fileName) {
        amazonS3Service.deleteFile(fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
