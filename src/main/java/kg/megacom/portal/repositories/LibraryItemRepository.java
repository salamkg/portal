package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {
}
