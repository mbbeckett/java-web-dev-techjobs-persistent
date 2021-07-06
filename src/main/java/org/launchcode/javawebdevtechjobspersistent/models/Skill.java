package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
public class Skill extends AbstractEntity {

    @ManyToMany(mappedBy = "skills")
    private ArrayList<Job> jobs;

    @NotNull
    @NotBlank(message = "Description is required.")
    @Size(min = 8, max = 200)
    private String description;

    public Skill(){}

    public Skill(ArrayList<Job> jobs, String description) {
        this.jobs = jobs;
        this.description = description;
    }
    public ArrayList<Job> getJobs() { return jobs;}

    public Skill(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}