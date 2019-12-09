package br.com.vfc.ppmtool.web.errors;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@JsonRootName("errors")
@XmlRootElement(name = "messageErrorApi")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageErrors {

    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
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
