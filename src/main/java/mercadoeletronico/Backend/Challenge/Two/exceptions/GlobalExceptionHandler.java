package mercadoeletronico.Backend.Challenge.Two.exceptions;

import mercadoeletronico.Backend.Challenge.Two.dtos.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(404, "Attempt to access a/an " + ex.getResourceName()
            + " that does not exist.");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCreationAttemptException.class)
    public ResponseEntity<ErrorMessage> handleDuplicateCreationAttemptException(DuplicateCreationAttemptException ex) {
        ErrorMessage errorMessage = new ErrorMessage(404, "Attempt to create a "
                + ex.getResourceName() + " that conflicts with an already existing unique identifier ( " +
                ex.getIdentifierName() + " with value = " + ex.getId());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
