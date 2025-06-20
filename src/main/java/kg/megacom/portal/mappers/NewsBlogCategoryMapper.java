package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.NewsBlogCategoryDTO;
import kg.megacom.portal.models.entities.NewsBlogCategory;

public interface NewsBlogCategoryMapper {
    NewsBlogCategoryDTO toDTO(NewsBlogCategory newsBlogCategory);
}
