package kg.megacom.portal.controllers;

import kg.megacom.portal.utils.LocalizationService;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        //TODO remove
        String message1 = LocalizationService.getMessage(langId, "helloMsg");
        String message2 = LocalizationService.getMessage(langId, "welcomeMsg");
        System.out.println(message1 + " " + message2);

        return ResponseEntity.ok(employees);
    }



}
