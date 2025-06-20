package kg.megacom.portal.models.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String companyNumber;
    private String mobileNumber;
    private String position;
    private String workPlace;
    private String workOffice;
    private String identificator_1C;
    private DepartmentDTO departmentName;
}
