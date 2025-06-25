package kg.megacom.portal.controllers;

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
public class NewsController {

    @Autowired
    private NewsBlogService newsBlogService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String title, @RequestParam String content, @RequestParam Long categoryId,
                                    @RequestParam(required = false) List<MultipartFile> files) {
        newsBlogService.create(title, content, categoryId, files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<NewsBlogDTO>> getAll() {
        List<NewsBlogDTO> newsBlogList = newsBlogService.getAll();
        return ResponseEntity.ok(newsBlogList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsBlogDTO> get(@PathVariable Long id) {
        NewsBlogDTO newsBlogDTO = newsBlogService.getById(id);
        return ResponseEntity.ok(newsBlogDTO);
    }

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestParam String name) {
        newsBlogService.addCategory(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<NewsBlogCategoryDTO>> getCategories() {
        List<NewsBlogCategoryDTO> newsBlogCategories = newsBlogService.getAllCategories();
        return ResponseEntity.ok(newsBlogCategories);
    }
}
