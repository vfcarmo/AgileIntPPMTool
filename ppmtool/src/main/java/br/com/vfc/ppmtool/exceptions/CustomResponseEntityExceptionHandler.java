package br.com.vfc.ppmtool.exceptions;

import br.com.vfc.ppmtool.web.errors.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProjectConflictException.class)
    public ResponseEntity<?> handleProjectConflictException(ProjectConflictException ex, WebRequest request) {
        MessageError error = new MessageError("project", "projectIdentifier",
                ex.getRejectedValue(), ex.getLocalizedMessage());
        return new ResponseEntity<MessageError>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    public ResponseEntity<?> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request) {
        MessageError error = new MessageError("project", ex.getLocalizedMessage());
        return new ResponseEntity<MessageError>(error, HttpStatus.NOT_FOUND);
    }
}
