package br.com.vfc.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserConflictException extends RuntimeException {

    private String username;

    public UserConflictException(String rejectedValue) {
        super(ErrorCode.USER_CONFLICT);
        this.username = rejectedValue;
    }

    public String getUsername() {
        return username;
    }
}
