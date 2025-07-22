package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.NewsBlogCategoryDTO;
import kg.megacom.portal.models.entities.NewsBlogCategory;
import org.springframework.stereotype.Component;

@Component
public class NewsBlogCategoryMapperImpl implements NewsBlogCategoryMapper {
    @Override
    public NewsBlogCategoryDTO toDTO(NewsBlogCategory newsBlogCategory) {
        return NewsBlogCategoryDTO.builder()
                .id(newsBlogCategory.getId())
                .name(newsBlogCategory.getName())
                .createdBy(newsBlogCategory.getCreatedBy().getFullName())
                .updatedBy(newsBlogCategory.getUpdatedBy() != null
                        ? newsBlogCategory.getUpdatedBy().getFullName()
                        : null)
                .createdAt(newsBlogCategory.getCreatedAt())
                .updatedAt(newsBlogCategory.getUpdatedAt())
                .build();
    }
}
