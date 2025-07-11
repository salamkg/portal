package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.BestEmployeesCreationException;
import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.mappers.EmployeeMapper;
import kg.megacom.portal.models.CreateBestEmployeesResponse;
import kg.megacom.portal.models.dto.BestEmployeeDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.BestEmployee;
import kg.megacom.portal.models.enums.AwardType;
import kg.megacom.portal.repositories.BestEmployeeRepository;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static kg.megacom.portal.utils.LocalizationService.getMessage;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private BestEmployeeRepository bestEmployeeRepository;

    @Override
    public List<EmployeeDTO> findAll(int pageNumber, int limit) {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employee -> employeeMapper.toEmployeeDTO(employee))
                .collect(Collectors.toList());

        int start = pageNumber * limit;
        int end = Math.min(start + limit, employeeDTOs.size());
        List<EmployeeDTO> paginatedList = employeeDTOs.subList(start, end);

        return paginatedList;
    }

    @Override
    public List<BestEmployeeDTO> getBestEmployees(Integer year) {
        List<BestEmployee> bestEmployeesByYear = bestEmployeeRepository.findAllByYear(year);
        return bestEmployeesByYear.stream()
                .map(e -> employeeMapper.toBestEmployeeDTO(e))
                .toList();
    }

    @Override
    public CreateBestEmployeesResponse createBestEmployees(Integer langId, Long employeeId, AwardType awardType, Integer year) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EmployeeNotFoundException(getMessage(langId, "employeeNotFound")));

            BestEmployee bestEmployee = BestEmployee.builder()
                    .employee(employee)
                    .awardType(awardType)
                    .year(year)
                    .build();
            bestEmployeeRepository.save(bestEmployee);

            return CreateBestEmployeesResponse.builder()
                    .bestEmployeeId(bestEmployee.getId())
                    .build();
        } catch (BestEmployeesCreationException e) {
            log.error("Error while creating best employees: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
