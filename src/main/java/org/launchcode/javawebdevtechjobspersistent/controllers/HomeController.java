package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

//THIS CONTROLLER METHOD IS WHERE THE PROBLEM LIES
//NO JOB OBJECTS BEING CREATED
//    NOTHING IS GOING INTO THE JOB REPO
//    INSTRUCTIONS IMPLY I NEED TO ADD ANOTHER REQUESTPARAM? LIST<JOB> JOBS?
//    I AM CONFUSED ABT THE @REQUESTPARAM LIST<INTEGER> SKILLS - HAS SOMETHING TO DO WITH MAPPED BY "SKILLS" -
//    WOULD THE LIST BE ALL OF THE SKILLS IDS OR AM I LOSING MY MIND?
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute(new Job());
            return "add";
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if (optionalEmployer.isPresent()){
            Employer employer = optionalEmployer.get();
            if(employer.getJobs().contains(newJob)){
                newJob.setEmployer(employer);
                jobRepository.save(newJob);
            }
        }
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional result = jobRepository.findById(jobId);
        if(result.isPresent()){
            Job job = (Job) result.get();
            model.addAttribute("job", job);
            return "view/{jobId}";
        }else {
            return "redirect:../";
        }
    }
}