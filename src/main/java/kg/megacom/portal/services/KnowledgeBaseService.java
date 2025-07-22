package kg.megacom.portal.services;

import kg.megacom.portal.models.CreateApplicationItemResponse;
import kg.megacom.portal.models.CreateLibraryItemResponse;
import kg.megacom.portal.models.dto.ApplicationItemDTO;
import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.dto.LibraryItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KnowledgeBaseService {
    List<LibraryItemDTO> findAllLibrary();

    void addField(Integer langId, String fieldName);

    KnowledgeFieldDTO findField(Long id);

    CreateLibraryItemResponse createLibraryItem(String itemName, String author, Long fieldId, int quantity, List<MultipartFile> libraryFile);

    List<KnowledgeFieldDTO> getAllFields();

    LibraryItemDTO findLibraryItem(Long id);

    CreateApplicationItemResponse createApplicationItem(Integer langId, String name, List<MultipartFile> files);

    void editApplicationItem(Long id, String title, List<MultipartFile> files);

    List<ApplicationItemDTO> getAllApplicationItems();
}
