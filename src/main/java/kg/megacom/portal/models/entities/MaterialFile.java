package kg.megacom.portal.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialFile {
    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "library_item_id")
    private LibraryItem libraryItem;
}
