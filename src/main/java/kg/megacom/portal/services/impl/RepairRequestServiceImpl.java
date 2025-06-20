package kg.megacom.portal.services.impl;

import kg.megacom.portal.exceptions.EmployeeNotFoundException;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.RepairRequest;
import kg.megacom.portal.repositories.EmployeeRepository;
import kg.megacom.portal.repositories.RepairRequestRepository;
import kg.megacom.portal.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    @Autowired
    private RepairRequestRepository repairRequestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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

    public Employee getCurrentEmployee() {
        Employee employee = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee;
    }
}
