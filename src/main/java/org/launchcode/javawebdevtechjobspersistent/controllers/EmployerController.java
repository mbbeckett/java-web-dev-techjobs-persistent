package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public String displayAllEmployers(Model model){
        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("/add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute("title", "Add Employer");
        model.addAttribute("employer", new Employer());
        return "employers/add";
    }

    @PostMapping("/add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(new Employer());
            return "redirect:";
        }
        employerRepository.save(newEmployer);
        return "employers/index";
    }

    @GetMapping("/view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
            Optional optEmployer = employerRepository.findById(employerId);
            if (optEmployer.isPresent()) {
                Employer employers = (Employer) optEmployer.get();
                model.addAttribute("employers", employers);
                model.addAttribute("employerId", employerId);
                return "view/{employerId}";
        }
            return "redirect:";
    }
}








//            } else {
//                model.addAttribute("title", "All Employers");
//                model.addAttribute("Employers", employerRepository.findAll());