package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.mappers.NewsBlogMapper;
import kg.megacom.portal.models.dto.NewsBlogDTO;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.NewsBlog;
import kg.megacom.portal.models.entities.NewsBlogCategory;
import kg.megacom.portal.models.entities.NewsBlogFile;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.repositories.NewsBlogCategoryRepository;
import kg.megacom.portal.repositories.NewsBlogRepository;
import kg.megacom.portal.services.NewsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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

    @Override
    public void create(String title, String content, Long categoryId, List<MultipartFile> newsBlogFiles) {
        //TODO get logged in user
        Employee employee = getCurrentEmployee();

        NewsBlog newsBlog = new NewsBlog();
        newsBlog.setTitle(title);
        newsBlog.setContent(content);
        newsBlog.setCreatedBy(employee);

        //Get Category by id
        NewsBlogCategory newsBlogCategory = newsBlogCategoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        newsBlog.setNewsBlogCategory(newsBlogCategory);

        //Attach Files
        if (newsBlogFiles != null && !newsBlogFiles.isEmpty()) {
            String uploadDir = "uploads/news";
            for (MultipartFile file : newsBlogFiles) {
                String fileName = file.getOriginalFilename();
                String filePath = uploadDir + UUID.randomUUID() + "_" + fileName;

                NewsBlogFile newsBlogFile = new NewsBlogFile();
                newsBlogFile.setFileName(fileName);
                newsBlogFile.setFilePath(filePath);
                newsBlogFile.setNewsBlog(newsBlog);

                newsBlog.getNewsBlogFiles().add(newsBlogFile);
            }
        }

        newsBlogRepository.save(newsBlog);
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
    public void addCategory(String name) {
        //TODO get logged in user
        Employee employee = getCurrentEmployee();

        NewsBlogCategory newsBlogCategory = new NewsBlogCategory();
        newsBlogCategory.setName(name);
        newsBlogCategory.setCreatedBy(employee);
        newsBlogCategoryRepository.save(newsBlogCategory);
    }

    @Override
    public NewsBlogDTO getById(Long id) {
        NewsBlog newsBlog = newsBlogRepository.findById(id).orElseThrow(() -> new RuntimeException("News Blog not found"));
        NewsBlogDTO newsBlogDTO = newsBlogMapper.toDTO(newsBlog);
        return newsBlogDTO;
    }


    public Employee getCurrentEmployee() {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee;
    }
}
