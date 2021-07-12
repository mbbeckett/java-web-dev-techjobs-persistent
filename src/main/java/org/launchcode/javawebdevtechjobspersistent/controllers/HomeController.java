package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
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

//    ADDED JOBREPO INSTANCE. NOT LISTED IN INSTRUCTIONS
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
//        YES THIS WORKS NOW DON'T YOU DARE MESS WITH THIS
        return "add";
    }
//THIS CONTROLLER METHOD IS WHERE THE PROBLEM LIES
//NEED TO CONNECT EMPLOYER OBJECT TO JOB TABLE
//    ADD LIST<JOBS> AS @REQUESTPARAM BECAUSE IT CREATES A COMMON FIELD WITH SKILL AND EMPLOYER
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam Integer employerId,
                                    @RequestParam List<Integer> skills,
                                    @RequestParam List<Job> jobs) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
//        if(employerId==null){
//            model.addAttribute("jobs", jobRepository.findAll());
//        } else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//            if (result.isEmpty()) {
//                model.addAttribute("title", "Invalid ID " + employerId);
//            } else {
//                Employer employer = result.get();
//                model.addAttribute("employers", employerRepository.findById(employerId));
//                jobRepository.save(newJob);
//            }
//        }
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }


}