package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.exceptions.KnowledgeFieldNotFoundException;
import kg.megacom.portal.exceptions.LibraryItemCreationException;
import kg.megacom.portal.mappers.KnowledgeFieldMapper;
import kg.megacom.portal.mappers.LibraryItemMapper;
import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.dto.LibraryItemDTO;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.KnowledgeField;
import kg.megacom.portal.models.entities.LibraryItem;
import kg.megacom.portal.models.entities.MaterialFile;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.repositories.KnowledgeFieldRepository;
import kg.megacom.portal.repositories.LibraryItemRepository;
import kg.megacom.portal.services.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {
    @Autowired
    private KnowledgeFieldRepository knowledgeFieldRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LibraryItemRepository libraryItemRepository;
    @Autowired
    private LibraryItemMapper libraryItemMapper;
    @Autowired
    private KnowledgeFieldMapper knowledgeFieldMapper;

    @Override
    public List<LibraryItemDTO> findAllLibrary() {
        List<LibraryItem> libraryItemList = libraryItemRepository.findAll();

        List<LibraryItemDTO> libraryItemDTOList = libraryItemList.stream()
                .map(libraryItem -> libraryItemMapper.toDTO(libraryItem))
                .toList();

        return libraryItemDTOList;
    }

    @Override
    public void addField(String fieldName) {
        //TODO get logged in user
        Employee employee = getCurrentEmployee();

        KnowledgeField knowledgeField = new KnowledgeField();
        knowledgeField.setName(fieldName);
        knowledgeField.setCreatedBy(employee);
        knowledgeFieldRepository.save(knowledgeField);
    }

    @Override
    public KnowledgeFieldDTO findField(Long fieldId) {
        KnowledgeField knowledgeField = knowledgeFieldRepository.findById(fieldId).orElseThrow(() -> new RuntimeException("Field not found"));
        return knowledgeFieldMapper.toDTO(knowledgeField);
    }

    @Override
    public void createLibraryItem(String itemName, String author, Long fieldId, int quantity, List<MultipartFile> libraryFiles) {
        try {
            LibraryItem libraryItem = new LibraryItem();
            libraryItem.setName(itemName);
            libraryItem.setAuthor(author);
            libraryItem.setCopies(quantity);
            libraryItem.setLocation("location");
            libraryItem.setCreatedAt(new Date());

            //Get field by id
            KnowledgeField field = knowledgeFieldRepository.findById(fieldId)
                    .orElseThrow(() -> new KnowledgeFieldNotFoundException("Тематика не найдена"));
            libraryItem.setField(field);

            if (libraryFiles != null && !libraryFiles.isEmpty()) {
                String uploadDir = "uploads/";
                for (MultipartFile file : libraryFiles) {
                    String fileName = file.getOriginalFilename();
                    String filePath = uploadDir + UUID.randomUUID() + "_" + fileName;

                    MaterialFile materialFile = new MaterialFile();
                    materialFile.setFileName(fileName);
                    materialFile.setFilePath(filePath);
                    materialFile.setLibraryItem(libraryItem);
                    libraryItem.getFiles().add(materialFile);
                }
            }

            libraryItemRepository.save(libraryItem);
        } catch (Exception ex) {
            throw new LibraryItemCreationException("Не удалось создать литературу: " + ex.getMessage());
        }
    }

    public Employee getCurrentEmployee() {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee;
    }
}
