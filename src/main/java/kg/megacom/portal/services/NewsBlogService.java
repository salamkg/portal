package kg.megacom.portal.services;

import kg.megacom.portal.models.dto.NewsBlogDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsBlogService {
    void create(String title, String content, Long categoryId, List<MultipartFile> newsBlogFiles);

    List<NewsBlogDTO> getAll();

    void addCategory(String name);

    NewsBlogDTO getById(Long id);
}
