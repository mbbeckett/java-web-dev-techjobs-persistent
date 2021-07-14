package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @NotNull
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    @NotNull
    @Size(max = 250)
    private String description;

    public Skill() { }

    public Skill(List<Job> jobs, String description) {
        this.jobs = jobs;
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

//    public void setJobs(List<Job> jobs) { this.jobs = jobs; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }
}