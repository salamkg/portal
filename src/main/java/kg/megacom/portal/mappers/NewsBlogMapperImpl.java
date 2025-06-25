package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.NewsBlogDTO;
import kg.megacom.portal.models.dto.NewsBlogFileDTO;
import kg.megacom.portal.models.entities.NewsBlog;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NewsBlogMapperImpl implements NewsBlogMapper {
    @Override
    public NewsBlogDTO toDTO(NewsBlog newsBlog) {
        return NewsBlogDTO.builder()
                .id(newsBlog.getId())
                .title(newsBlog.getTitle())
                .content(newsBlog.getContent())
                .category(newsBlog.getNewsBlogCategory().getName())
                .createdBy(newsBlog.getCreatedBy().getFullName())
                .updatedBy(newsBlog.getCreatedBy().getFullName() != null
                        ? newsBlog.getCreatedBy().getFullName()
                        : null
                )
                .createdAt(newsBlog.getCreatedAt())
                .updatedAt(newsBlog.getUpdatedAt())
                .newsBlogFiles(newsBlog.getNewsBlogFiles().stream()
                        .map(newsBlogFile -> NewsBlogFileDTO.builder()
                                .fileName(newsBlogFile.getFileName())
                                .filePath(newsBlogFile.getFilePath())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
