package kg.megacom.portal.controllers;

import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?> getEmployees(int pageNumber, int limit) {

        List<EmployeeDTO> employees = employeeService.findAll(pageNumber, limit);

        return ResponseEntity.ok(employees);
    }



}
