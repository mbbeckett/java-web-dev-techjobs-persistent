package org.launchcode.javawebdevtechjobspersistent.models.dto;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;

import javax.validation.constraints.NotNull;

public class JobEmployerDTO {

    @NotNull
    private Job job;

    @NotNull
    private Employer employer;

    public JobEmployerDTO(){}

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
