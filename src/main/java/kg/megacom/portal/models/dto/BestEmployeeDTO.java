package kg.megacom.portal.models.dto;


import kg.megacom.portal.models.enums.AwardType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BestEmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String position;
    private AwardType awardType;
    private Integer year;
}
