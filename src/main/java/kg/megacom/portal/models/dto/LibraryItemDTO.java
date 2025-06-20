package kg.megacom.portal.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemDTO {
    private int number;
    private String name;
    private String author;
    private KnowledgeFieldDTO field;
    private int copies;
    private String location;
    private List<MaterialFileDTO> libraryFiles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
}
