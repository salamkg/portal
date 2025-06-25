package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.DepartmentDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee.EmployeeBuilder builder = Employee.builder();
        return builder
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .middleName(employeeDTO.getMiddleName())
                .email(employeeDTO.getEmail())
                .companyNumber(employeeDTO.getCompanyNumber())
                .mobileNumber(employeeDTO.getMobileNumber())
                .position(employeeDTO.getPosition())
                .workPlace(employeeDTO.getWorkPlace())
                .workOffice(employeeDTO.getWorkOffice())
                .identificator_1C(employeeDTO.getIdentificator_1C())
                .build();
    }

    @Override
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .middleName(employee.getMiddleName())
                .email(employee.getEmail())
                .companyNumber(employee.getCompanyNumber())
                .mobileNumber(employee.getMobileNumber())
                .position(employee.getPosition())
                .workPlace(employee.getWorkPlace())
                .workOffice(employee.getWorkOffice())
                .identificator_1C(employee.getIdentificator_1C())
                .departmentName(
                        DepartmentDTO.builder()
                                .name(employee.getDepartment().getName())
                                .build()
                )
                .build();
    }
}
