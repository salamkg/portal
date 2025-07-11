package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.ApplicationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationItemRepository extends JpaRepository<ApplicationItem, Long> {
}
