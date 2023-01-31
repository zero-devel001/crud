package com.sharsheev.crud.controllers;

import com.sharsheev.crud.dao.PersonDao;
import com.sharsheev.crud.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDao personDao;

    public AdminController(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping()
    public String adminPanel(Model model , @ModelAttribute("person") Person person ) {
        model.addAttribute("people",personDao.index());

        return "adminPanel";
    }
    @PostMapping("/add")
    public String makeAdmin() {
        return "redirect:/people";

//        Person admin = personDao.findById(person.getId());
//        if (admin == null) {
//            System.err.println("Person not found!");
//        }
//        System.out.println(admin.getName() + "appointed as admin");
    }
}
