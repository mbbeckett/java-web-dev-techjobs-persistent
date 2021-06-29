package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters.")
    private String location;

    public Employer(){}

    public Employer(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    Represents the list of all items in a given job
    /*one to many relationship mapped by not specified to do this in the directions. same with final
    * one employer with many jobs
    * how to determine which jobs correspond to an employer*/
//    mapped by employer field of the Job class
    @OneToMany
    @JoinColumn
    private final List<Job> jobs = new ArrayList<>();

    public List<Job> getJobs() { return jobs; }
}
