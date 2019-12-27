package br.com.vfc.ppmtool.web.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ProjectUpdateRequest {

    @NotBlank(message = "Project name is required.")
    private String projectName;

    @NotBlank(message = "Project description is required.")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("start_date")
    @Column(name = "start_date")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("end_date")
    @Column(name = "end_date")
    private Date endDate;

    public ProjectUpdateRequest() {
    }

    public ProjectUpdateRequest(String projectName, String description, Date startDate, Date endDate) {
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}
