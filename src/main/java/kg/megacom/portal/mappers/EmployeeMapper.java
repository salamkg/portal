package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.entities.Employee;

public interface EmployeeMapper {

    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(Employee employee);
}
