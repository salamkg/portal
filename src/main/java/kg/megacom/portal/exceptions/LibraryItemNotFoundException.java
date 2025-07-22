package kg.megacom.portal.exceptions;

public class LibraryItemNotFoundException extends RuntimeException {
    public LibraryItemNotFoundException(String message) {
        super(message);
    }
}
