package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

//    FIELDS
//    TODO: MANY TO MANY RELATIONSHIP WITH JOBS
    @ManyToMany(mappedBy = "skills")
    private final List<Job> jobs = new ArrayList<>();

    @NotNull
    @NotBlank(message = "Description is required.")
    @Size(min = 8, max = 200)
    private String description;

//    CONSTRUCTORS
    public Skill(String description) {
    this.description = description;
}

    public Skill(){}

    //    GETTERS SETTERS
    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}