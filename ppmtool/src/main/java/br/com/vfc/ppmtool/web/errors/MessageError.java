package br.com.vfc.ppmtool.web.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeName("error")
@JsonPropertyOrder(value = {"type", "title", "detail", "status", "instance", "help" })
@XmlType(name = "error", propOrder = {"type", "title", "detail", "status", "instance", "help" })
public class MessageError {

    /** A URI identifier that categorizes the error. */
    private String type;

    /** A brief, human-readable message about the error. */
    private String title;

    /** The HTTP response coee (optional). */
    private String status;

    /** A human-readable explanation of the error. */
    private String detail;

    /** A URI that identifies the specific occurrence of the error. */
    private String instance;

    /** A URL that clients can follow to discover more information. */
    private String help;

    public MessageError() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
