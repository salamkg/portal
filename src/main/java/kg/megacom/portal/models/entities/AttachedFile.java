package kg.megacom.portal.models.entities;

import jakarta.persistence.*;
import kg.megacom.portal.models.enums.ItemType;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "attached_files")
public class AttachedFile {
    @Id
    @GeneratedValue
    private Long id;

    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private String filePath;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id")
//    private BaseFile owner;
    private Long ownerId;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;
}
