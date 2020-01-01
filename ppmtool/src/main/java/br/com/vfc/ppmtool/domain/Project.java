package br.com.vfc.ppmtool.domain;

import br.com.vfc.ppmtool.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@JsonTypeName(value = "project")
@JsonPropertyOrder(value = {"id", "projectName", "projectIdentifier",
        "description", "startDate", "endDate", "createdAt", "updatedAt" })
@JsonInclude(Include.NON_EMPTY)
public class Project extends BaseEntity {

    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String projectName;

    @NotBlank(message = ErrorCode.NOT_BLANK)
    @Size(min = 4, max = 5, message = ErrorCode.SIZE)
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("start_date")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("end_date")
    private Date endDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

    public Project() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
        backlog.setProject(this);
    }
}
