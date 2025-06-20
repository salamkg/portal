package kg.megacom.portal.mappers;

import kg.megacom.portal.models.dto.LibraryItemDTO;
import kg.megacom.portal.models.entities.LibraryItem;


public interface LibraryItemMapper {
    LibraryItemDTO toDTO(LibraryItem libraryItem);
}
