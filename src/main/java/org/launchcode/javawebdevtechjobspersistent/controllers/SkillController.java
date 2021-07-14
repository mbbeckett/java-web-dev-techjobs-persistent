package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    private String index(Model model){
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    @GetMapping("add")
    private String displayAddSkillForm(Model model){
        model.addAttribute(new Skill());
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/add";
    }

    @PostMapping("add")
    private String processAddSkillForm(@ModelAttribute @Valid Skill newSkill, Errors errors, Model model){

        if(errors.hasErrors()){
            return "skills/add";
        }

        model.addAttribute("skill", newSkill);
        skillRepository.save(newSkill);
        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    private String displayViewSkill(Model model, @PathVariable int skillId){
        Optional optSkill = skillRepository.findById(skillId);
        if(optSkill.isPresent()){
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "view";
        } else {
            return "redirect:../";
        }
    }

}