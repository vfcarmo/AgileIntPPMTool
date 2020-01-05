package br.com.vfc.ppmtool.security;

import br.com.vfc.ppmtool.exceptions.ErrorCode;
import br.com.vfc.ppmtool.web.errors.MessageError;
import br.com.vfc.ppmtool.web.errors.MessageErrors;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private MessageSource messageSource;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        MessageErrors messageErrors = new MessageErrors();
        messageErrors.getErrors().add(buildMessageError(request, status, ErrorCode.USERNAME_INVALID));
        messageErrors.getErrors().add(buildMessageError(request, status, ErrorCode.PASSWORD_INVALID));

        String jsonMessageErrors = new Gson().toJson(messageErrors);

        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(jsonMessageErrors);
    }

    private MessageError buildMessageError(HttpServletRequest request, HttpStatus status, String keyMessage) {
        MessageError error = new MessageError();
        String errorCode = keyMessage
                .replace("error.", "")
                .replace(".message", "");
        error.setType(String.format("errors/%s", errorCode));
        error.setTitle(getMessage(keyMessage, request.getLocale()));
        error.setStatus(String.valueOf(status.value()));
        error.setHelp(String.format("%s/%s", getMessage(ErrorCode.ERROR_PAGE, request.getLocale()), errorCode ));
        error.setInstance(request.getRequestURI());
        return error;
    }

    private String getMessage(String keyMessage, Locale locale, Object... args) {
        String message;
        try {
            message = messageSource.getMessage(keyMessage, args, locale);
        } catch (Exception e) {
            message = keyMessage;
        }
        return message;
    }


}
