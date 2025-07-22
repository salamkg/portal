package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.ApplicationItemDTO;
import kg.megacom.portal.models.dto.AttachedFileDTO;
import kg.megacom.portal.models.entities.ApplicationItem;
import kg.megacom.portal.models.entities.AttachedFile;
import kg.megacom.portal.models.enums.ItemType;
import kg.megacom.portal.repositories.AttachedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationItemMapperImpl implements ApplicationItemMapper {
    @Autowired
    private AttachedFileRepository attachedFileRepository;

    @Override
    public ApplicationItemDTO toDTO(ApplicationItem applicationItem) {

        List<AttachedFile> files = attachedFileRepository.findByItemTypeAndOwnerId(ItemType.ApplicationItem, applicationItem.getId());
        return ApplicationItemDTO.builder()
                .id(applicationItem.getId())
                .title(applicationItem.getTitle())
                .createdBy(applicationItem.getCreatedBy().getFullName())
                .updatedBy(applicationItem.getUpdatedBy() != null
                        ? applicationItem.getUpdatedBy().getFullName()
                        : null)
                .createdAt(applicationItem.getCreatedAt())
                .updatedAt(applicationItem.getUpdatedAt())
                .files(
                        files == null
                                ? Collections.emptyList()
                                : files.stream().map(file ->
                                        AttachedFileDTO.builder()
                                                .originalFileName(file.getOriginalFileName())
                                                .filePath(file.getFilePath())
                                                .build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
