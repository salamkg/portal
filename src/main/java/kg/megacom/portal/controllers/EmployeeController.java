package kg.megacom.portal.controllers;

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
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?> getEmployees(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                          @RequestParam(required = false, defaultValue = "25") Integer limit, @RequestParam(required = false) Integer langId) {

        List<EmployeeDTO> employees = employeeService.findAll(pageNumber, limit);

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/best-people")
    public List<BestEmployeeDTO> getBestEmployees(@RequestParam Integer year) {
        List<BestEmployeeDTO> bestEmployees = employeeService.getBestEmployees(year);
        return bestEmployees;
    }

    @PostMapping("/best-people/create")
    public ResponseEntity<?> createBestEmployee(@RequestBody BestEmployeeDTO bestEmployeeDTO) {
        employeeService.createBestEmployees(bestEmployeeDTO);
        return ResponseEntity.ok().build();
    }



}
