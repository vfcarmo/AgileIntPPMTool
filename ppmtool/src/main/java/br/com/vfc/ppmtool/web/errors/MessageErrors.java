package br.com.vfc.ppmtool.web.errors;

import java.util.ArrayList;
import java.util.List;

public class MessageErrors {

    private List<MessageError> errors;

    public MessageErrors() {
        this.errors = new ArrayList<>();
    }

    public MessageErrors(MessageError error) {
        this();
        this.errors.add(error);
    }

    public List<MessageError> getErrors() {
        return errors;
    }

    public void setErrors(List<MessageError> errors) {
        this.errors = errors;
    }
}
