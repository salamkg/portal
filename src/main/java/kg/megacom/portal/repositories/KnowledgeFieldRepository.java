package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.KnowledgeField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeFieldRepository extends JpaRepository<KnowledgeField, Long> {
}
