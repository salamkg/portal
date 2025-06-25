package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.LibraryItemDTO;
import kg.megacom.portal.models.dto.MaterialFileDTO;
import kg.megacom.portal.models.entities.LibraryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LibraryItemMapperImpl implements LibraryItemMapper {
    @Autowired
    private KnowledgeFieldMapper knowledgeFieldMapper;
    @Override
    public LibraryItemDTO toDTO(LibraryItem libraryItem) {

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
                .libraryFiles(
                        libraryItem.getFiles().stream().map(file ->
                            MaterialFileDTO.builder()
                                    .fileName(file.getFileName())
                                    .filePath(file.getFilePath())
                                    .build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
