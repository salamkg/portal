package kg.megacom.portal.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachedFileDTO {
    private String originalFileName;
    private String filePath;
}
