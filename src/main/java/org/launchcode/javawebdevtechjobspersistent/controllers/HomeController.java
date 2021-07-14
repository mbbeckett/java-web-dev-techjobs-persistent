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

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam Integer employerId,
                                    @RequestParam List<Integer> skills){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
//        if (employerId == null) {
//            model.addAttribute("title", "All Jobs");
//            model.addAttribute("jobs", jobRepository.findAll());
//            return "add";
//        } else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//            if (result.isEmpty()) {
//                model.addAttribute("title", "Invalid Employer ID: " + employerId);
//            } else {
//                Employer employer = result.get();
//                model.addAttribute("title", "Jobs with Employer: " + employer.getName());
//                model.addAttribute("jobs", employer.getJobs());
//            }
//        }
//
//        if(skills==null){
//            model.addAttribute("title", "All Jobs");
//            model.addAttribute("jobs", jobRepository.findAll());
//            return "add";
//        } else {
//            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//            if(skillObjs.isEmpty()){
//                model.addAttribute("title", "Invalid Skill ID: "+ skills);
//            } else{
//                newJob.setSkills(skillObjs);
//                model.addAttribute("title", "Jobs with Skills: " + skillObjs);
//                model.addAttribute("jobs", newJob.getSkills());
//            }
//        }
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        Employer employer = employerRepository.findById(employerId).orElse(new Employer());
        newJob.setEmployer(employer);

        jobRepository.save(newJob);
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