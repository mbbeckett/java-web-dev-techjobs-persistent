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

    @ManyToMany(mappedBy = "jobs")
    @NotNull
    @NotBlank(message = "Skills required.")
    @Size(min = 8, max = 200)
    private Skill skills;

    public Job() {
    }

    public Job(Employer employer, Skill skills) {
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

    public Skill getSkills() { return skills; }

    public void setSkills(Skill skills) {
        this.skills = skills;
    }
}
