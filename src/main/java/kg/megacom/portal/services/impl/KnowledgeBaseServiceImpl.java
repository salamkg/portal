package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.exceptions.KnowledgeFieldNotFoundException;
import kg.megacom.portal.exceptions.LibraryItemCreationException;
import kg.megacom.portal.mappers.KnowledgeFieldMapper;
import kg.megacom.portal.mappers.LibraryItemMapper;
import kg.megacom.portal.models.CreateLibraryItemResponse;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
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
    public void addField(Integer langId, String fieldName) {
        try {
            //TODO get logged in user
            Employee employee = getCurrentEmployee();

            KnowledgeField knowledgeField = KnowledgeField.builder()
                    .name(fieldName)
                    .createdBy(employee)
                    .build();
            knowledgeFieldRepository.save(knowledgeField);

        } catch (RuntimeException e) {
            log.error("Error while adding field: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public KnowledgeFieldDTO findField(Long fieldId) {
        KnowledgeField knowledgeField = knowledgeFieldRepository.findById(fieldId).orElseThrow(() -> new RuntimeException("Field not found"));
        return knowledgeFieldMapper.toDTO(knowledgeField);
    }

    @Override
    public CreateLibraryItemResponse createLibraryItem(String itemName, String author, Long fieldId, int quantity, List<MultipartFile> libraryFiles) {
        try {
            //TODO get logged in user
            Employee employee = getCurrentEmployee();

            LibraryItem libraryItem = LibraryItem.builder()
                    .name(itemName)
                    .author(author)
                    .copies(quantity)
                    .location("location")
                    .createdAt(new Date())
                    .createdBy(employee)
                    .build();

            //Get field by id
            KnowledgeField field = knowledgeFieldRepository.findById(fieldId)
                    .orElseThrow(() -> new KnowledgeFieldNotFoundException("Тематика не найдена"));
            libraryItem.setField(field);

            if (libraryFiles != null && !libraryFiles.isEmpty()) {
                String uploadDir = "uploads/";
                for (MultipartFile file : libraryFiles) {
                    String fileName = file.getOriginalFilename();
                    String filePath = uploadDir + UUID.randomUUID() + "_" + fileName;

                    MaterialFile materialFile = MaterialFile.builder()
                            .fileName(fileName)
                            .filePath(filePath)
                            .libraryItem(libraryItem)
                            .build();
                    if (libraryItem.getFiles() == null) {
                        libraryItem.setFiles(new ArrayList<>());
                    }
                    libraryItem.getFiles().add(materialFile);
                }
            }

            libraryItemRepository.save(libraryItem);

            return CreateLibraryItemResponse.builder()
                    .libraryItemId(libraryItem.getId())
                    .build();
        } catch (Exception ex) {
            throw new LibraryItemCreationException("Error creating Library Item: " + ex.getMessage());
        }
    }

    @Override
    public List<KnowledgeFieldDTO> getAllFields() {
        List<KnowledgeField> knowledgeFieldList = knowledgeFieldRepository.findAll();
        List<KnowledgeFieldDTO> knowledgeFieldDTOList = knowledgeFieldList.stream()
                .map(knowledgeField -> knowledgeFieldMapper.toDTO(knowledgeField))
                .toList();
        return knowledgeFieldDTOList;
    }

    @Override
    public LibraryItemDTO findLibraryItem(Long id) {
        LibraryItem libraryItem = libraryItemRepository.findById(id).orElse(null);
        return libraryItemMapper.toDTO(libraryItem);
    }

    public Employee getCurrentEmployee() {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee;
    }
}
