
package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @OneToMany
    @JoinColumn
    private List<Job> jobs;

    @NotNull
    private String location;

    public Employer() {}

    public Employer(List<Job> jobs, String location) {
        this.jobs = jobs;
        this.location = location;
    }

    public Employer(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {this.jobs = jobs;}

    public String getLocation() { return location; }

    public void setLocation(String location) {
        this.location = location;
    }

}