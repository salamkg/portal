package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.NewsBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsBlogRepository extends JpaRepository<NewsBlog, Long> {
}
