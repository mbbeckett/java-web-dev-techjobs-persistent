package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Job extends AbstractEntity{

    @ManyToOne
    @NotNull(message = "Employer required.")
    private Employer employer;
//there can be many jobs for each employer but one employer per job.

    @NotNull
    @NotBlank(message = "Skills required.")
    @Size(min = 8, max = 200)
    private String skills;

    public Job() {
    }

    public Job(Employer employer, String skills) {
        super();
        this.employer = employer;
        this.skills = skills;
    }

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getSkills() { return skills; }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
