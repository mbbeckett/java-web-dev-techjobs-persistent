package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

//    FIELDS
    @ManyToOne
    @NotNull(message = "Employer required.")
    private Employer employer;

//    TODO: WHAT DATA TYPE IS THE SKILLS FIELD?
    @ManyToMany
    @Size(min = 8, max = 200)
    private List<Skill> skills = new ArrayList<>();

//    CONSTRUCTORS
    public Job(Employer employer) {
        this.employer = employer;
    }

    public Job(List<Skill> skills) {
        this.skills = skills;
    }

    public Job() { }

//    GETTERS SETTERS
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() { return skills; }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
