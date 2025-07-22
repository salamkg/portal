package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.ApplicationItemDTO;
import kg.megacom.portal.models.entities.ApplicationItem;

public interface ApplicationItemMapper {
    ApplicationItemDTO toDTO(ApplicationItem applicationItem);
}
