package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.RepairRequestDTO;
import kg.megacom.portal.models.entities.RepairRequest;
import org.springframework.stereotype.Component;

@Component
public class RepairRequestMapperImpl implements RepairRequestMapper {
    @Override
    public RepairRequestDTO toDTO(RepairRequest repairRequest) {
        return RepairRequestDTO.builder()
                .id(repairRequest.getId())
                .email(repairRequest.getEmail())
                .repairDescription(repairRequest.getRepairDescription())
                .repairLocation(repairRequest.getRepairLocation())
                .department(repairRequest.getDepartment())
                .phoneNumber(repairRequest.getPhoneNumber())
                .createdAt(repairRequest.getCreatedAt())
                .employeeFullName(repairRequest.getEmployeeFullName())
                .build();
    }
}
