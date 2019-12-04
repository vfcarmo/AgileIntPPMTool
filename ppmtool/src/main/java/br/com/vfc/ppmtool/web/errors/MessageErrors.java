package br.com.vfc.ppmtool.web.errors;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class MessageErrors {

    private List<MessageError> errors;

    public MessageErrors() {
        this.errors = new ArrayList<>();
    }

    public MessageErrors(List<FieldError> fieldErrors) {
        this();
        for (FieldError field : fieldErrors) {
            String rejectedValue = (field.getRejectedValue() != null)
                    ? field.getRejectedValue().toString() : "null";
            errors.add(new MessageError(field.getObjectName(),
                    field.getField(), rejectedValue, field.getDefaultMessage()));
        }
    }

    public List<MessageError> getErrors() {
        return errors;
    }

    public void setErrors(List<MessageError> errors) {
        this.errors = errors;
    }
}
