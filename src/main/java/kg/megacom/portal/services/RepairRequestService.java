package kg.megacom.portal.services;

import kg.megacom.portal.models.dto.RepairRequestDTO;

import java.util.List;

public interface RepairRequestService {
    void createRepairRequest(String repairLocation, String repairDescription);

    List<RepairRequestDTO> getAllRepairRequests();
}
