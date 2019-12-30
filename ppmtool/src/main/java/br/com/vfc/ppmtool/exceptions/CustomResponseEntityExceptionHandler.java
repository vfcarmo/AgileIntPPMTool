package br.com.vfc.ppmtool.exceptions;

import br.com.vfc.ppmtool.web.errors.MessageError;
import br.com.vfc.ppmtool.web.errors.MessageErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE_SUFIX = ".message";
    private static final String ERROR_DETAIL_SUFIX = ".detail";
    private static final String ERROR_TYPE_FORMAT = "errors/%s";
    private static final String ERROR_HELP_FORMAT = "%s/%s";
    private static final String ERROR_MESSAGE_PREFIX = "error.default.";
    private static final String STANDARD_ERROR_URI_FORMAT = "standards/%s";

    private final MessageSource messageSource;

    @Autowired
    public CustomResponseEntityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        MessageError[] errors = result.getAllErrors()
                .stream()
                .map(objectError -> buildMessageError(objectError.getDefaultMessage(),
                        status, request, objectError.getArguments()))
                .toArray(MessageError[]::new);
        return buildResponseEntity(status, request, errors);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.MISSING_REQUEST_PARAMETER,
                HttpStatus.BAD_REQUEST, request, ex.getParameterName());
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.MEDIA_TYPE_NOT_ACCEPTABLE,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, ex.getSupportedMediaTypes());
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.MEDIA_TYPE_NOT_SUPPORTABLE,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, ex.getSupportedMediaTypes());
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(ErrorCode.JSON_REQUEST_MALFORMED,
                HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildDefaultResponseEntity(request);
    }

    @ExceptionHandler(value = ProjectConflictException.class)
    public ResponseEntity<?> handleProjectConflictException(ProjectConflictException ex, WebRequest request) {
        return buildResponseEntity(ErrorCode.PROJECT_CONFLICT, HttpStatus.CONFLICT, request, ex.getRejectedValue());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> handleProjectNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, request, ex.getArgs());
    }

    private ResponseEntity<Object> buildResponseEntity(String keyMessage,
                                                       HttpStatus status, WebRequest request, Object... args) {

        return buildResponseEntity(status, request, buildMessageError(keyMessage, status, request, args));
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status,
                                                              WebRequest request, MessageError... errors) {
        MessageErrors messageErrors = new MessageErrors();
        messageErrors.getErrors().addAll(Arrays.asList(errors));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(messageErrors, headers, status);
    }

    private ResponseEntity<Object> buildDefaultResponseEntity(WebRequest request) {

        String keyMessage = ErrorCode.INTERNAL_SERVER_ERROR;

        return buildResponseEntity(keyMessage, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private MessageError buildMessageError(String keyMessage,
                                           HttpStatus status, WebRequest request, Object... args) {
        MessageError error = new MessageError();
        String errorCode = keyMessage
                .replace(ERROR_MESSAGE_PREFIX, "")
                .replace(ERROR_MESSAGE_SUFIX, "");
        error.setType(String.format(ERROR_TYPE_FORMAT, errorCode));
        error.setTitle(getMessage(keyMessage, request.getLocale(), args));
        error.setDetail(getDetailMessage(keyMessage, request.getLocale(), args));
        error.setStatus(String.valueOf(status.value()));
        error.setHelp(String.format(ERROR_HELP_FORMAT, getMessage(ErrorCode.ERROR_PAGE, request.getLocale()),
                (keyMessage.startsWith(ERROR_MESSAGE_PREFIX)) ?
                        String.format(STANDARD_ERROR_URI_FORMAT, errorCode) : errorCode ));
        error.setInstance(getDecodeUrl(request));
        return error;
    }

    private String getDecodeUrl(WebRequest request) {
        String instance;
        try {
            instance = ((ServletWebRequest) request).getRequest().getRequestURI();
        } catch (Exception e) {
            instance = null;
        }
        return instance;
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

    private String getDetailMessage(String keyMessage, Locale locale, Object... args) {

        keyMessage = (keyMessage.endsWith(ERROR_MESSAGE_SUFIX)) ?
                keyMessage.replace(ERROR_MESSAGE_SUFIX, ERROR_DETAIL_SUFIX) : null;

        return getMessage(keyMessage, locale, args);
    }
}
