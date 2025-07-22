package kg.megacom.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.megacom.portal.models.CreateApplicationItemResponse;
import kg.megacom.portal.models.CreateLibraryItemResponse;
import kg.megacom.portal.models.dto.ApplicationItemDTO;
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
@Tag(name = "База знаний")
public class KnowledgeBaseController {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @Operation(summary = "Библиотека")
    @GetMapping("/libraryItems")
    public ResponseEntity<List<LibraryItemDTO>> getLibraryItems() {
        List<LibraryItemDTO> libraryList = knowledgeBaseService.findAllLibrary();
        return ResponseEntity.ok(libraryList);
    }

    @Operation(summary = "Просмотр библиотеки")
    @GetMapping("/libraryItem/{id}")
    public ResponseEntity<LibraryItemDTO> getLibraryItem(@PathVariable Long id) {
        LibraryItemDTO libraryItem = knowledgeBaseService.findLibraryItem(id);
        return ResponseEntity.ok(libraryItem);
    }

    @Operation(summary = "Создание библиотеки")
    @PostMapping(value = "/libraryItem/create", consumes = "multipart/form-data")
    public ResponseEntity<?> createLibraryItem(@RequestParam String itemName, @RequestParam String author, @RequestParam Long fieldId,
                                               @RequestParam int quantity, @RequestParam(required = false) List<MultipartFile> files) {
        CreateLibraryItemResponse createLibraryItemResponse = knowledgeBaseService.createLibraryItem(itemName, author, fieldId, quantity, files);
        return ResponseEntity.ok(createLibraryItemResponse);
    }

    @Operation(summary = "Просмотр тематик библиотеки")
    @GetMapping("/all-fields")
    public ResponseEntity<List<KnowledgeFieldDTO>> getKnowledgeFields() {
        List<KnowledgeFieldDTO> knowledgeFieldList = knowledgeBaseService.getAllFields();
        return ResponseEntity.ok(knowledgeFieldList);
    }

    @Operation(summary = "Просмотр тематики библиотеки")
    @GetMapping("/fieldById")
    public ResponseEntity<KnowledgeFieldDTO> getKnowledgeField(@RequestParam Long id) {
        KnowledgeFieldDTO knowledgeFieldDTO = knowledgeBaseService.findField(id);
        return ResponseEntity.ok(knowledgeFieldDTO);
    }

    @Operation(summary = "Создание тематики библиотеки")
    @PostMapping("/add-field")
    public ResponseEntity<?> addField(@RequestParam(required = false) Integer langId, @RequestParam(name = "field") String fieldName) {
        knowledgeBaseService.addField(langId, fieldName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Создание шаблона заявления")
    @PostMapping(value = "/applicationItem/create", consumes = "multipart/form-data")
    public ResponseEntity<?> createApplicationItem(@RequestParam(required = false) Integer langId, @RequestParam String title,
                                                   @RequestParam(required = false) List<MultipartFile> files) {
        CreateApplicationItemResponse response = knowledgeBaseService.createApplicationItem(langId, title, files);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Редактирование шаблона заявления")
    @PutMapping(path = "/applicationItem/{id}/edit", consumes = "multipart/form-data")
    public ResponseEntity<?> editApplicationItem(@PathVariable Long id, @RequestParam(required = false) String title,
                                                 @RequestParam(required = false) List<MultipartFile> files) {
        knowledgeBaseService.editApplicationItem(id, title, files);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Просмотр шаблонов заявления")
    @GetMapping("/applicationItems")
    public ResponseEntity<List<ApplicationItemDTO>> getAllApplicationItems() {
        List<ApplicationItemDTO> applicationDto = knowledgeBaseService.getAllApplicationItems();
        return ResponseEntity.ok(applicationDto);
    }

}
