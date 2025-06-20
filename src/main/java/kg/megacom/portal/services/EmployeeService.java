package kg.megacom.portal.services;

import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll(int pageNumber, int limit);
}
