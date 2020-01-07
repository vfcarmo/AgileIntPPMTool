package br.com.vfc.ppmtool.web.requests;

import br.com.vfc.ppmtool.exceptions.ErrorCode;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String username;

    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String password;

    public LoginRequest() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
