package org.example.service;

import org.example.entity.Document;
import org.example.entity.HomeLoanApplication;
import org.example.repository.DocumentRepository;
import org.example.repository.HomeLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private HomeLoanRepository loanRepository;

    public Document uploadDocument(Long loanId, String documentType, MultipartFile file) throws IOException {
        HomeLoanApplication loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Create upload folder if not exists
        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        // Generate unique file name
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // Save file locally
        file.transferTo(new File(filePath));

        Document doc = new Document();
        doc.setLoan(loan);
        doc.setDocumentType(documentType);
        doc.setFilePath(filePath);

        return documentRepository.save(doc);
    }

    public List<Document> getDocumentsForLoan(Long loanId) {
        HomeLoanApplication loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return documentRepository.findAll().stream()
                .filter(d -> d.getLoan().getId().equals(loanId))
                .toList();
    }
}
