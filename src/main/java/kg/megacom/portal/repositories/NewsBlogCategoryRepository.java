package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.NewsBlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsBlogCategoryRepository extends JpaRepository<NewsBlogCategory, Long> {
}
