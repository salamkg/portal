package kg.megacom.portal.models;

import kg.megacom.portal.models.entities.Employee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeesPaginated {
    private long count;
    private List<Employee> employeeList;
}
