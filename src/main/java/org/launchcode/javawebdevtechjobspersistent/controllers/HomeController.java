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
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("jobs", jobRepository.findAll());
        return "/add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model,
                                    @RequestParam Integer employerId,
                                    @RequestParam List<Integer> skills) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "/add";
        }
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if(optionalEmployer.isPresent()){
            Employer employerParam = optionalEmployer.get();
            model.addAttribute("employer", employerParam);
            model.addAttribute("skills", newJob.getSkills());
        }
        return "/view";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        if(optionalJob.isEmpty()){
            model.addAttribute("title", "Invalid Category ID: " + jobId);
        } else {
            Job job = optionalJob.get();
            model.addAttribute("job", job.getId());
        }
        return "/view/{jobId}";
    }
}









//        what is happening in this block of code?
//        skills.get(employerId);
//        model.addAttribute("employerId", employerId);
//        model.addAttribute("skills", newJob.getSkills());
//        employerRepository.save(newJob.getEmployer());

//    Optional optJob = employerRepository.findById(jobId);
//        if(optJob.isPresent()){
//                Job job = (Job) optJob.get();
//                model.addAttribute("job", job);
//                return "view/{jobId}";
//                }
//                return "redirect:";


//processAddJobForm?
// employerRepository.save(newJob.getEmployer());


//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        } else {
//            Optional<Employer> employerRepositoryById = employerRepository.findById(employerId);
//            if(employerRepositoryById.isEmpty()){
//                model.addAttribute("title", "Invalid ID" + employerId);
//            } else {
//
//                model.addAttribute("employers", newJob.getEmployer());
//                model.addAttribute("skills", newJob.getSkills());
//            }
//        }

