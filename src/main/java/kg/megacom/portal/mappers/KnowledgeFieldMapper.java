package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.KnowledgeFieldDTO;
import kg.megacom.portal.models.entities.KnowledgeField;

public interface KnowledgeFieldMapper {
    KnowledgeFieldDTO toDTO(KnowledgeField knowledgeField);
}
