package kg.megacom.portal.services;

import kg.megacom.portal.models.dto.BestEmployeeDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll(int pageNumber, int limit);

    List<BestEmployeeDTO> getBestEmployees(Integer year);

    void createBestEmployees(Integer langId, BestEmployeeDTO bestEmployeeDTO);
}
