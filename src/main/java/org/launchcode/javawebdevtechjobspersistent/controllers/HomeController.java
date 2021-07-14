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
//        model.addAttribute("jobs", jobRepository.findAll());
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

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam Integer employerId,
                                    @RequestParam List<Integer> skills){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            System.out.println("hi");
            return "add";
        }

//        Employer employer = employerRepository.findById(employerId).orElse(new Employer());
//        newJob.setEmployer(employer);
//        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);


//        if(employerId==null){
//            model.addAttribute("title", "Invalid Employer ID");
//        } else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//            if(result.isEmpty()) {
//                model.addAttribute("title", "Invalid Employer ID");
//            } else {
//                Employer employer = result.get();
//                model.addAttribute("employerId", employer.getJobs());
//                newJob.setEmployer(employer);
//                List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//                newJob.setSkills(skillObjs);
//
//            }
//        }
//        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
//        Optional result = jobRepository.findById(jobId);
//        if(result.isPresent()){
//            Job job = (Job) result.get();
//            model.addAttribute("job", job);
//        model.addAttribute("job", jobRepository.findById(jobId));
            return "view";
    }
}


//        Employer employer = employerRepository.findById(employerId).orElse(new Employer());
//        newJob.setEmployer(employer);
//        jobRepository.save(newJob);

//        Optional result = employerRepository.findById(employerId);
//        if(result.isPresent()){
//            Employer employer = (Employer) result.get();
//            model.addAttribute("jobs", employer.getJobs());
//        }

//                model.addAttribute("employers", employer.getJobs());
//                model.addAttribute("employerId", employer.getId());
//                model.addAttribute("employers", employer.getName());