package kg.megacom.portal.services;

import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.dto.LibraryItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KnowledgeBaseService {
    List<LibraryItemDTO> findAllLibrary();

    void addField(String fieldName);

    KnowledgeFieldDTO findField(Long id);

    void createLibraryItem(String itemName, String author, Long fieldId, int quantity, List<MultipartFile> libraryFile);
}
