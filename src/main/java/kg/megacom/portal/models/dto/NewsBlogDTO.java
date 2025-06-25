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
public class NewsBlogDTO {
    private Long id;
    private String title;
    private String content;
    private List<NewsBlogFileDTO> newsBlogFiles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date updatedAt;

    private String category;
    private String createdBy;
    private String updatedBy;
}
