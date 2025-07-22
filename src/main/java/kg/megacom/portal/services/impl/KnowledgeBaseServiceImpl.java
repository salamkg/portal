package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.exceptions.KnowledgeFieldNotFoundException;
import kg.megacom.portal.exceptions.LibraryItemCreationException;
import kg.megacom.portal.exceptions.LibraryItemNotFoundException;
import kg.megacom.portal.mappers.ApplicationItemMapper;
import kg.megacom.portal.mappers.KnowledgeFieldMapper;
import kg.megacom.portal.mappers.LibraryItemMapper;
import kg.megacom.portal.models.CreateApplicationItemResponse;
import kg.megacom.portal.models.CreateLibraryItemResponse;
import kg.megacom.portal.models.dto.ApplicationItemDTO;
import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.dto.LibraryItemDTO;
import kg.megacom.portal.models.entities.*;
import kg.megacom.portal.models.enums.ItemType;
import kg.megacom.portal.repositories.*;
import kg.megacom.portal.services.KnowledgeBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private ApplicationItemMapper applicationItemMapper;
    @Autowired
    private ApplicationItemRepository applicationItemRepository;
    @Autowired
    private AttachedFileRepository attachedFileRepository;

    @Override
    public List<LibraryItemDTO> findAllLibrary() {
        List<LibraryItem> libraryItemList = libraryItemRepository.findAll();

        return libraryItemList.stream()
                .map(libraryItem -> libraryItemMapper.toDTO(libraryItem))
                .toList();
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
        KnowledgeField knowledgeField = knowledgeFieldRepository.findById(fieldId).orElseThrow(() -> new KnowledgeFieldNotFoundException("Field not found"));
        return knowledgeFieldMapper.toDTO(knowledgeField);
    }

    @Override
    public CreateLibraryItemResponse createLibraryItem(String itemName, String author, Long fieldId, int quantity, List<MultipartFile> files) {
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

            libraryItemRepository.save(libraryItem);

            //attach files and save
            saveAttachedFile(files, libraryItem.getId(), ItemType.LibraryItem);

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
        return knowledgeFieldList.stream()
                .map(knowledgeField -> knowledgeFieldMapper.toDTO(knowledgeField))
                .toList();
    }

    @Override
    public LibraryItemDTO findLibraryItem(Long id) {
        LibraryItem libraryItem = libraryItemRepository.findById(id).orElseThrow(() -> new LibraryItemNotFoundException("LibraryItem not found"));
        return libraryItemMapper.toDTO(libraryItem);
    }

    @Override
    public CreateApplicationItemResponse createApplicationItem(Integer langId, String title, List<MultipartFile> files) {
        ApplicationItem applicationItem = ApplicationItem.builder()
                .title(title)
                .createdAt(new Date())
                .createdBy(getCurrentEmployee()) //TODO current user
                .build();

        applicationItemRepository.save(applicationItem);

        //attach files and save
        saveAttachedFile(files, applicationItem.getId(), ItemType.ApplicationItem);

        return CreateApplicationItemResponse.builder()
                .applicationId(applicationItem.getId())
                .build();
    }

    @Override
    public void editApplicationItem(Long id, String title, List<MultipartFile> files) {
        ApplicationItem applicationItem = applicationItemRepository.findById(id).orElseThrow(()
                -> new RuntimeException("ApplicationItem " + id + " not found"));

        if (title != null && !title.equals(applicationItem.getTitle())) {
            applicationItem.setTitle(title);
            applicationItem.setUpdatedBy(applicationItem.getCreatedBy()); //TODO current user
            applicationItem.setUpdatedAt(new Date());
        }
        if (files != null && !files.isEmpty()) {
            //remove old files
            List<AttachedFile> attachedFiles = attachedFileRepository.findByItemTypeAndOwnerId(ItemType.ApplicationItem, id);
            attachedFiles.forEach(attachedFile -> attachedFileRepository.deleteById(attachedFile.getId()));
            //attach files and save
            saveAttachedFile(files, applicationItem.getId(), ItemType.ApplicationItem);
        }

        applicationItemRepository.save(applicationItem);
    }

    @Override
    public List<ApplicationItemDTO> getAllApplicationItems() {
        List<ApplicationItem> applicationItems = applicationItemRepository.findAll();
        return applicationItems.stream()
                .map(item -> applicationItemMapper.toDTO(item))
                .toList();
    }

    public void saveAttachedFile(List<MultipartFile> files, Long ownerId, ItemType itemType) {
        if (files != null && !files.isEmpty()) {
            String uploadDir = "uploads/";
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String storedFileName = uploadDir + UUID.randomUUID() + "_" + fileName;
                String filePath = uploadDir + storedFileName;

                AttachedFile attachedFile = AttachedFile.builder()
                        .originalFileName(fileName)
                        .storedFileName(storedFileName)
                        .fileType(file.getContentType())
                        .filePath(filePath)
                        .ownerId(ownerId)
                        .itemType(itemType)
                        .build();
                attachedFileRepository.save(attachedFile);
            }
        }
    }

    public Employee getCurrentEmployee() {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee;
    }
}
