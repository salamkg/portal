package kg.megacom.portal.services;

import kg.megacom.portal.models.dto.NewsBlogCategoryDTO;
import kg.megacom.portal.models.dto.NewsBlogDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsBlogService {
    void create(Integer langId, String title, String content, Long categoryId, List<MultipartFile> newsBlogFiles);

    List<NewsBlogDTO> getAll();

    void addCategory(Integer langId, String name);

    NewsBlogDTO getById(Long id, Integer langId);

    List<NewsBlogCategoryDTO> getAllCategories();
}
