package kg.megacom.portal.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.megacom.portal.controllers.EmployeeController;
import kg.megacom.portal.controllers.KnowledgeBaseController;
import kg.megacom.portal.models.CreateBestEmployeesResponse;
import kg.megacom.portal.models.CreateLibraryItemResponse;
import kg.megacom.portal.models.dto.*;
import kg.megacom.portal.models.enums.AwardType;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.services.EmployeeService;
import kg.megacom.portal.services.KnowledgeBaseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KnowledgeBaseController.class)
@ActiveProfiles("dev")
public class KnowledgeBaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KnowledgeBaseService knowledgeBaseService;
    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_URL = "/api/v1/knowledge-base";

    //TODO исправить
    @Test
    void createLibraryItem_ShouldReturnOk() throws Exception {
        String itemName = "itemName";
        String author = "author";
        Long fieldId = 1L;
        Integer quantity = 10;
        MockMultipartFile file1 = new MockMultipartFile("file", "test.json", "application/json", new byte[]{});
        MockMultipartFile file2 = new MockMultipartFile("file", "test2.json", "application/json", new byte[]{});
        List<MultipartFile> files = List.of(file1, file2);


        CreateLibraryItemResponse response = CreateLibraryItemResponse.builder()
                .libraryItemId(1L)
                .build();

        when(knowledgeBaseService.createLibraryItem(
                eq(itemName), eq(author), eq(fieldId),
                eq(quantity), eq(files))).thenReturn(response);

        mockMvc.perform(multipart(BASE_URL + "/addLibraryItem")
                .file(file1)
                .file(file2)
                .param("itemName", itemName)
                .param("author", author)
                .param("fieldId", String.valueOf(1L))
                .param("quantity", String.valueOf(quantity))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libraryItemId").value(1L));
    }
}
