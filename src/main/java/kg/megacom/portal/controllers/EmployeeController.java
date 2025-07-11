package kg.megacom.portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.megacom.portal.models.CreateBestEmployeesResponse;
import kg.megacom.portal.models.dto.BestEmployeeDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees")
@Tag(name = "Сотрудники компании")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Список всех сотрудников")
    @GetMapping()
    public ResponseEntity<?> getEmployees(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                          @RequestParam(required = false, defaultValue = "25") Integer limit, @RequestParam(required = false) Integer langId) {

        List<EmployeeDTO> employees = employeeService.findAll(pageNumber, limit);

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Список лучших сотрудников")
    @GetMapping("/best-people")
    public List<BestEmployeeDTO> getBestEmployees(@RequestParam Integer year) {
        List<BestEmployeeDTO> bestEmployees = employeeService.getBestEmployees(year);
        return bestEmployees;
    }

    @Operation(summary = "Создание лучшего сотрудника")
    @PostMapping("/best-people/create")
    public ResponseEntity<?> createBestEmployee(@RequestParam(required = false) Integer langId, @RequestBody BestEmployeeDTO bestEmployeeDTO) {
        CreateBestEmployeesResponse createBestEmployeesResponse = employeeService.createBestEmployees(langId, bestEmployeeDTO);
        return ResponseEntity.ok(createBestEmployeesResponse);
    }



}
