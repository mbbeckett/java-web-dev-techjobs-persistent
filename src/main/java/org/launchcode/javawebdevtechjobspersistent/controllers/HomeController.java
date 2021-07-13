package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.launchcode.javawebdevtechjobspersistent.models.dto.JobSkillDTO;
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

//    ADDED JOBREPO INSTANCE. NOT LISTED IN INSTRUCTIONS
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
//NEED TO CONNECT EMPLOYER OBJECT TO JOB TABLE
//    INSTRUCTIONS IMPLY I NEED TO ADD ANOTHER REQUESTPARAM? LIST<JOB> JOBS?
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam Integer employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if(optionalEmployer.isPresent()){
            Employer employer = optionalEmployer.get();
            newJob.setEmployer(employer);
        }
        jobRepository.save(newJob);
        return "redirect:";
    }


//ISSUE IS WITH VIEW AND PROCESS CONTROLLERS
//    NOTHING IS GOING INTO THE JOB REPO
    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional result = jobRepository.findById(jobId);
        if(result.isPresent()){
            Job jobs = (Job) result.get();
            model.addAttribute("jobs", jobs);
            return "view/{jobId}";
        }
        return "redirect:../";
    }
}