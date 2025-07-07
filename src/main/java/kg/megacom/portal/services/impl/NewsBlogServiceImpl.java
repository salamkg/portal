package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.*;
import kg.megacom.portal.mappers.NewsBlogCategoryMapper;
import kg.megacom.portal.mappers.NewsBlogMapper;
import kg.megacom.portal.models.dto.NewsBlogCategoryDTO;
import kg.megacom.portal.models.dto.NewsBlogDTO;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.NewsBlog;
import kg.megacom.portal.models.entities.NewsBlogCategory;
import kg.megacom.portal.models.entities.NewsBlogFile;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.repositories.NewsBlogCategoryRepository;
import kg.megacom.portal.repositories.NewsBlogRepository;
import kg.megacom.portal.services.NewsBlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static kg.megacom.portal.utils.LocalizationService.getMessage;

@Slf4j
@Service
public class NewsBlogServiceImpl implements NewsBlogService {
    @Autowired
    private NewsBlogRepository newsBlogRepository;
    @Autowired
    private NewsBlogCategoryRepository newsBlogCategoryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NewsBlogMapper newsBlogMapper;
    @Autowired
    private NewsBlogCategoryMapper newsBlogCategoryMapper;

    @Override
    public void create(Integer langId, String title, String content, Long categoryId, List<MultipartFile> newsBlogFiles) {
        try {
            //TODO get logged in user
            Employee employee = getCurrentEmployee(langId);

            NewsBlog newsBlog = NewsBlog.builder()
                    .title(title)
                    .content(content)
                    .createdBy(employee)
                    .build();

            //Get Category by id
            NewsBlogCategory newsBlogCategory = newsBlogCategoryRepository.findById(categoryId).orElseThrow(() ->
                    new NewsBlogCategoryNotFoundException(getMessage(langId, "newsBlogCategoryNotFound")));
            newsBlog.setNewsBlogCategory(newsBlogCategory);

            //Attach Files
            if (newsBlogFiles != null && !newsBlogFiles.isEmpty()) {
                String uploadDir = "uploads/news";
                for (MultipartFile file : newsBlogFiles) {
                    String fileName = file.getOriginalFilename();
                    String filePath = uploadDir + UUID.randomUUID() + "_" + fileName;

                    NewsBlogFile newsBlogFile = NewsBlogFile.builder()
                            .fileName(fileName)
                            .filePath(filePath)
                            .newsBlog(newsBlog)
                            .build();

                    if (newsBlog.getNewsBlogFiles() == null) {
                        newsBlog.setNewsBlogFiles(new ArrayList<>());
                    }
                    newsBlog.getNewsBlogFiles().add(newsBlogFile);
                }
            }
            newsBlogRepository.save(newsBlog);

        } catch (NewsBlogCreationException e) {
            log.error("Error while creating news blog: {}", e.getMessage());
            throw new RuntimeException("Failed to create new news blog " + e.getMessage());
        }


    }

    @Override
    public List<NewsBlogDTO> getAll() {
        List<NewsBlog> newsBlogs = newsBlogRepository.findAll();
        List<NewsBlogDTO> newsBlogDTOs = newsBlogs.stream()
                .map(newsBlog -> newsBlogMapper.toDTO(newsBlog))
                .toList();
        return newsBlogDTOs;
    }

    @Override
    public void addCategory(Integer langId, String name) {
        try {
            //TODO get logged in user
            Employee employee = getCurrentEmployee(langId);

            NewsBlogCategory newsBlogCategory = NewsBlogCategory.builder()
                    .name(name)
                    .createdBy(employee)
                    .build();
            newsBlogCategoryRepository.save(newsBlogCategory);
        } catch (NewsBlogCategoryCreationException e) {
            log.error("Error while adding category: {}", e.getMessage());
            throw new RuntimeException("Failed to add category " + e.getMessage());
        }
    }

    @Override
    public NewsBlogDTO getById(Long id, Integer langId) {
        NewsBlog newsBlog = newsBlogRepository.findById(id).orElseThrow(() -> new NewsBlogNotFoundException(getMessage(langId, "newsBlogNotFound")));
        NewsBlogDTO newsBlogDTO = newsBlogMapper.toDTO(newsBlog);
        return newsBlogDTO;
    }

    @Override
    public List<NewsBlogCategoryDTO> getAllCategories() {
        List<NewsBlogCategory> newsBlogCategories = newsBlogCategoryRepository.findAll();
        List<NewsBlogCategoryDTO> newsBlogCategoryDTOList = newsBlogCategories.stream()
                .map(newsBlogCategory -> newsBlogCategoryMapper.toDTO(newsBlogCategory))
                .toList();
        return newsBlogCategoryDTOList;
    }


    public Employee getCurrentEmployee(Integer langId) {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException(getMessage(langId, "employeeNotFound")));
        return employee;
    }
}
