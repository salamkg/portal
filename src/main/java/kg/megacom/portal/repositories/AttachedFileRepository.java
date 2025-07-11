package kg.megacom.portal.repositories;

import kg.megacom.portal.models.entities.AttachedFile;
import kg.megacom.portal.models.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachedFileRepository extends JpaRepository<AttachedFile, Long> {
    List<AttachedFile> findByItemTypeAndOwnerId(ItemType itemType, Long ownerId);
}
