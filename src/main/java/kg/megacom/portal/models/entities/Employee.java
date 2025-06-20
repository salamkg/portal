package kg.megacom.portal.models.entities;

import jakarta.persistence.*;
import kg.megacom.portal.models.enums.EmployeeRole;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Login info
    private String username;
    private String password;

    // Personal info
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String companyNumber;
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    private String position;
    private String workPlace;
    private String workOffice;
    private String identificator_1C;
    private String account;
    private String information;

    public String getFullName() {
        return firstName + " " + lastName + " " + middleName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Vacation info
    private Date hiringDate;
    private String identification;
    private int vacationDaysEarned;
    private int vacationDaysUsed;
    private int vacationDaysAvailable;
}
