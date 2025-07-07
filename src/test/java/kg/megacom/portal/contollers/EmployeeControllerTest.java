package kg.megacom.portal.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.megacom.portal.controllers.EmployeeController;
import kg.megacom.portal.models.CreateBestEmployeesResponse;
import kg.megacom.portal.models.dto.BestEmployeeDTO;
import kg.megacom.portal.models.dto.DepartmentDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.enums.AwardType;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ActiveProfiles("dev")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_URL = "/api/v1/employees";

    @Test
    void getEmployees_ShouldReturnOk() throws Exception {
        Integer pageNumber = 0;
        Integer limit = 10;

        EmployeeDTO emp1 = EmployeeDTO.builder()
                .email("email@email.com")
                .companyNumber("1212")
                .departmentName(DepartmentDTO.builder().name("DIT").build())
                .firstName("firstName")
                .lastName("lastName")
                .fullName("fullName")
                .identificator_1C("identificator_1C")
                .mobileNumber("213123123")
                .position("position")
                .workOffice("workOffice")
                .workPlace("workPlace")
                .build();

        EmployeeDTO emp2 = EmployeeDTO.builder()
                .email("email@email1.com")
                .companyNumber("1212")
                .departmentName(DepartmentDTO.builder().name("COM").build())
                .firstName("firstName")
                .lastName("lastName")
                .fullName("fullName")
                .identificator_1C("identificator_1C")
                .mobileNumber("213123123")
                .position("position")
                .workOffice("workOffice")
                .workPlace("workPlace")
                .build();

        List<EmployeeDTO> employeesList = new ArrayList<>();
        employeesList.add(emp1);
        employeesList.add(emp2);

        when(employeeService.findAll(eq(pageNumber), eq(limit))).thenReturn(employeesList);

        mockMvc.perform(get(BASE_URL)
                .param("pageNumber", pageNumber.toString())
                .param("limit", limit.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void createBestEmployee_ShouldReturnOk() throws Exception {
        Integer langId = 1;
        BestEmployeeDTO bestEmployeeDTO = BestEmployeeDTO.builder()
                .employeeId(1L)
                .year(2025)
                .awardType(AwardType.CRYSTAL)
                .firstName("John")
                .lastName("Doe")
                .position("Java Programmer")
                .build();

        CreateBestEmployeesResponse response = CreateBestEmployeesResponse.builder().bestEmployeeId(1L).build();

        when(employeeService.createBestEmployees(eq(langId), any(BestEmployeeDTO.class))).thenReturn(response);

        mockMvc.perform(post(BASE_URL + "/best-people/create")
                .param("langId", String.valueOf(langId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bestEmployeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bestEmployeeId").value(1));
    }
}
