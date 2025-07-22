package kg.megacom.portal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(kg.megacom.portal.exceptions.FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex) {
        Map<String, String> httpResponse = new HashMap<>();
        httpResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(kg.megacom.portal.exceptions.LibraryItemCreationException.class)
    public ResponseEntity<?> handleLibraryItemCreationException(LibraryItemCreationException ex) {
        Map<String, String> httpResponse = new HashMap<>();
        httpResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST); // 400 ?
    }

    @ExceptionHandler(kg.megacom.portal.exceptions.KnowledgeFieldNotFoundException.class)
    public ResponseEntity<?> handleKnowledgeFieldNotFoundException(KnowledgeFieldNotFoundException ex) {
        Map<String, String> httpResponse = new HashMap<>();
        httpResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND); // 404 ?
    }

    @ExceptionHandler(kg.megacom.portal.exceptions.NewsBlogNotFoundException.class)
    public ResponseEntity<?> handleNewsBlogNotFoundException(NewsBlogNotFoundException ex) {
        Map<String, String> httpResponse = new HashMap<>();
        httpResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND); // 404 ?
    }

    @ExceptionHandler(kg.megacom.portal.exceptions.LibraryItemNotFoundException.class)
    public ResponseEntity<?> LibraryItemNotFoundException(LibraryItemNotFoundException ex) {
        Map<String, String> httpResponse = new HashMap<>();
        httpResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND); // 404 ?
    }
}
