package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.mappers.RepairRequestMapper;
import kg.megacom.portal.models.dto.RepairRequestDTO;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.RepairRequest;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.repositories.RepairRequestRepository;
import kg.megacom.portal.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    @Autowired
    private RepairRequestRepository repairRequestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Override
    public void createRepairRequest(String repairLocation, String repairDescription) {
        //Get Employee
        Employee employee = getCurrentEmployee();

        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setRepairLocation(repairLocation);
        repairRequest.setRepairDescription(repairDescription);

        repairRequest.setEmployeeFullName(employee.getFirstName() + " " + employee.getLastName() + " " + employee.getMiddleName());
        repairRequest.setDepartment(employee.getDepartment().getName());
        repairRequest.setPhoneNumber(employee.getCompanyNumber());
        repairRequest.setEmail(employee.getEmail());
        repairRequest.setEmployee(employee);
        repairRequest.setCreatedAt(new Date());
        repairRequestRepository.save(repairRequest);
    }

    @Override
    public List<RepairRequestDTO> getAllRepairRequests() {
        List<RepairRequest> repairRequests = repairRequestRepository.findAll();
        List<RepairRequestDTO> repairRequestDTOList = repairRequests.stream()
                .map(repairRequest -> repairRequestMapper.toDTO(repairRequest))
                .toList();

        return repairRequestDTOList;
    }

    public Employee getCurrentEmployee() {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee;
    }
}
