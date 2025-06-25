package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.RepairRequestDTO;
import kg.megacom.portal.models.entities.RepairRequest;


public interface RepairRequestMapper {
    RepairRequestDTO toDTO(RepairRequest repairRequest);
}
