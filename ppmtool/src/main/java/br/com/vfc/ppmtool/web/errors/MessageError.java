package br.com.vfc.ppmtool.web.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageError {

    private String objectName;

    private String field;

    private String rejectedValue;

    private String defaultMessage;

    public MessageError() {
    }

    public MessageError(String defaultMessage) {
        this(null, defaultMessage);
    }

    public MessageError(String objectName, String defaultMessage) {
        this.objectName = objectName;
        this.defaultMessage = defaultMessage;
    }

    public MessageError(String objectName, String field, String rejectedValue, String defaultMessage) {
        this.objectName = objectName;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.defaultMessage = defaultMessage;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
