package br.com.vfc.ppmtool.web.requests;

import br.com.vfc.ppmtool.domain.Backlog;
import br.com.vfc.ppmtool.domain.BaseEntity;
import br.com.vfc.ppmtool.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ProjectTaskUpdateRequest {

    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String summary;

    private String acceptanceCriteria;

    private Integer priority;

    private String status;

    private Date dueDate;

    public ProjectTaskUpdateRequest() {
        super();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
