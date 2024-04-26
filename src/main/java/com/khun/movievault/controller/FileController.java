package com.khun.movievault.controller;

import com.khun.movievault.service.AzureStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movievault/v1/api/file")
public class FileController {

    @Autowired
    private AzureStorageService azureStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(azureStorageService.uploadBlob(file));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] fileContent = azureStorageService.downloadBlob(fileName);

        return ResponseEntity.ok()
                .contentLength(fileContent.length)
                .body(fileContent);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return ResponseEntity.ok(azureStorageService.deleteBlob(fileName));
    }
}
