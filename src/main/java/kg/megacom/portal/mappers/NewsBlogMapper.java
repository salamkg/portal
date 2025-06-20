package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.NewsBlogDTO;
import kg.megacom.portal.models.entities.NewsBlog;


public interface NewsBlogMapper {
    NewsBlogDTO toDTO(NewsBlog newsBlog);
}
