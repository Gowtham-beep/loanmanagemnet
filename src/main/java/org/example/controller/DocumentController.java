package org.example.controller;

import org.example.entity.Document;
import org.example.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/loans/{loanId}/documents")
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(
            @PathVariable Long loanId,
            @RequestParam("documentType") String documentType,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Document saved = documentService.uploadDocument(loanId, documentType, file);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getDocuments(@PathVariable Long loanId) {
        return ResponseEntity.ok(documentService.getDocumentsForLoan(loanId));
    }
}

