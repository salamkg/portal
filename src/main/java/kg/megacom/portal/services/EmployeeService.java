package kg.megacom.portal.services;

import kg.megacom.portal.models.CreateBestEmployeesResponse;
import kg.megacom.portal.models.dto.BestEmployeeDTO;
import kg.megacom.portal.models.dto.EmployeeDTO;
import kg.megacom.portal.models.enums.AwardType;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll(int pageNumber, int limit);

    List<BestEmployeeDTO> getBestEmployees(Integer year);

    CreateBestEmployeesResponse createBestEmployees(Integer langId, Long employeeId, AwardType awardType, Integer year);
}
