package kg.megacom.portal.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairRequestDTO {
    private Long id;
    private String repairLocation;
    private String repairDescription;
    private String employeeFullName;
    private String department;
    private String phoneNumber;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date createdAt;
//    private EmployeeDTO employee;
}
