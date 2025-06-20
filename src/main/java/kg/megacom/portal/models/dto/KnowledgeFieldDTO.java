package kg.megacom.portal.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.megacom.portal.models.entities.Employee;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeFieldDTO {
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
