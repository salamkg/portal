package kg.megacom.portal.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialFileDTO {
    private String fileName;
    private String filePath;
}
