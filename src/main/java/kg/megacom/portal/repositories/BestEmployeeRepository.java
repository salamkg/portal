package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.BestEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestEmployeeRepository extends JpaRepository<BestEmployee, Long> {
    List<BestEmployee> findAllByYear(Integer year);
}
