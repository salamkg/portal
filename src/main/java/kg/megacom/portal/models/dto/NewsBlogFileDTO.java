package kg.megacom.portal.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsBlogFileDTO {
    private String fileName;
    private String filePath;
}
