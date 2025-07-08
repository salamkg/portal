package kg.megacom.portal.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.megacom.portal.models.dto.NewsBlogCategoryDTO;
import kg.megacom.portal.models.dto.NewsBlogDTO;
import kg.megacom.portal.services.NewsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/news_blog")
@Tag(name = "Новости", description = "Новости компании")
public class NewsController {

    @Autowired
    private NewsBlogService newsBlogService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam(required = false) Integer langId, @RequestParam String title, @RequestParam String content, @RequestParam Long categoryId,
                                    @RequestParam(required = false) List<MultipartFile> files) {
        newsBlogService.create(langId, title, content, categoryId, files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<NewsBlogDTO>> getAll() {
        List<NewsBlogDTO> newsBlogList = newsBlogService.getAll();
        return ResponseEntity.ok(newsBlogList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsBlogDTO> get(@PathVariable Long id, @RequestParam(required = false) Integer langId) {
        NewsBlogDTO newsBlogDTO = newsBlogService.getById(id, langId);
        return ResponseEntity.ok(newsBlogDTO);
    }

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestParam Integer langId, @RequestParam String name) {
        newsBlogService.addCategory(langId, name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<NewsBlogCategoryDTO>> getCategories() {
        List<NewsBlogCategoryDTO> newsBlogCategories = newsBlogService.getAllCategories();
        return ResponseEntity.ok(newsBlogCategories);
    }
}
