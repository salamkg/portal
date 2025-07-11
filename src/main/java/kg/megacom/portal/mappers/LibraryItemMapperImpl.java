package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.AttachedFileDTO;
import kg.megacom.portal.models.dto.LibraryItemDTO;
import kg.megacom.portal.models.entities.AttachedFile;
import kg.megacom.portal.models.entities.LibraryItem;
import kg.megacom.portal.models.enums.ItemType;
import kg.megacom.portal.repositories.AttachedFileRepository;
import kg.megacom.portal.repositories.LibraryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryItemMapperImpl implements LibraryItemMapper {
    @Autowired
    private KnowledgeFieldMapper knowledgeFieldMapper;
    @Autowired
    private LibraryItemRepository libraryItemRepository;
    @Autowired
    private AttachedFileRepository attachedFileRepository;

    @Override
    public LibraryItemDTO toDTO(LibraryItem libraryItem) {
        if (libraryItem == null) return null;

        List<AttachedFile> files = attachedFileRepository.findByItemTypeAndOwnerId(ItemType.LibraryItem, libraryItem.getId());

        return LibraryItemDTO.builder()
                .id(libraryItem.getId())
                .name(libraryItem.getName())
                .author(libraryItem.getAuthor())
                .field(knowledgeFieldMapper.toDTO(libraryItem.getField()))
                .copies(libraryItem.getCopies())
                .location(libraryItem.getLocation())
                .createdAt(libraryItem.getCreatedAt())
                .updatedAt(libraryItem.getUpdatedAt())
                .createdBy(libraryItem.getCreatedBy().getFullName())
                .updatedBy(libraryItem.getUpdatedBy() != null
                        ? libraryItem.getUpdatedBy().getFullName()
                        : null
                )
                .files(
                        files == null
                                ? Collections.emptyList()
                                : files.stream().map(file ->
                            AttachedFileDTO.builder()
                                    .originalFileName(file.getOriginalFileName())
                                    .filePath(file.getFilePath())
                                    .build())
                                .collect(Collectors.toList())
                        ).build();
    }
}
