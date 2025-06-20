package kg.megacom.portal.controllers;

import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.dto.LibraryItemDTO;
import kg.megacom.portal.services.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/knowledge-base")
public class KnowledgeBaseController {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @GetMapping("/library")
    public ResponseEntity<List<LibraryItemDTO>> getLibrary() {
        List<LibraryItemDTO> libraryList = knowledgeBaseService.findAllLibrary();
        return ResponseEntity.ok(libraryList);
    }

    @PostMapping("/addLibraryItem")
    public ResponseEntity<LibraryItemDTO> createLibraryItem(@RequestParam String itemName, @RequestParam String author,
                                                         @RequestParam Long fieldId, @RequestParam int quantity,
                                                         @RequestParam(required = false) List<MultipartFile> libraryFiles) {
        knowledgeBaseService.createLibraryItem(itemName, author, fieldId, quantity, libraryFiles);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/fieldById")
    public ResponseEntity<KnowledgeFieldDTO> getViewField(@RequestParam Long id) {
        KnowledgeFieldDTO knowledgeFieldDTO = knowledgeBaseService.findField(id);
        return ResponseEntity.ok(knowledgeFieldDTO);
    }

    @PostMapping("/add-field")
    public ResponseEntity<?> addField(@RequestParam(name = "field") String fieldName) {
        knowledgeBaseService.addField(fieldName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
