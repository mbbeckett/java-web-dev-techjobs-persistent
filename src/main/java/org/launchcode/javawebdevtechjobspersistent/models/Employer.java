package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

//    FIELDS
//    TODO: ONE EMPLOYER TO MANY JOBS
    @OneToMany
    @JoinColumn
    private List<Job> jobs = new ArrayList<>();

    @NotNull
    @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters.")
    private String location;

//    CONSTRUCTORS
    public Employer(List<Job> jobs, String location) {
        this.jobs = jobs;
        this.location = location;
    }

    public Employer(){}

//    GETTERS SETTERS
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() { return jobs; }
}
