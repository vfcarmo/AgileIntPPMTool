package br.com.vfc.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProjectConflictException extends RuntimeException {

    private String rejectedValue;

    public ProjectConflictException(String rejectedValue) {
        super(ErrorCode.PROJECT_CONFLICT);
        this.rejectedValue = rejectedValue;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }
}
