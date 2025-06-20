package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.entities.KnowledgeField;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeFieldMapperImpl implements KnowledgeFieldMapper {
    @Override
    public KnowledgeFieldDTO toDTO(KnowledgeField knowledgeField) {
        KnowledgeFieldDTO knowledgeFieldDTO = KnowledgeFieldDTO.builder()
                .id(knowledgeField.getId())
                .name(knowledgeField.getName())
                .createdAt(knowledgeField.getCreatedAt())
                .updatedAt(knowledgeField.getUpdatedAt())
                .createdBy(knowledgeField.getCreatedBy().getFullName())
                .updatedBy(knowledgeField.getUpdatedBy().getFullName())
                .build();
        return knowledgeFieldDTO;
    }
}
