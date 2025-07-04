package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.BestEmployeeDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.models.entities.BestEmployee;

public interface EmployeeMapper {

    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(Employee employee);
    BestEmployeeDTO toBestEmployeeDTO(BestEmployee bestEmployee);
}
