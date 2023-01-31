package com.sharsheev.crud.controllers;

import com.sharsheev.crud.dao.PersonRepository;
import com.sharsheev.crud.model.Person;
import com.sharsheev.crud.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonValidator personValidator;
    private final PersonRepository personRepository;
    @Autowired
    public PeopleController( PersonValidator personValidator, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personValidator = personValidator;
    }

    @GetMapping("")
    public String index(Model model) {
        //Get all people from DAO and send to thymeleaf
        model.addAttribute("people",personRepository.findAll());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id , Model model) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent())
            model.addAttribute(person.get());
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person",new Person());
        return "people/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person ,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model , @PathVariable long id) {
        model.addAttribute("person", personRepository.findById(id).get());
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person ,BindingResult bindingResult
            , @PathVariable("id") int id){
        if (bindingResult.hasErrors()) return "people/edit";
        person.setId(id);
        personRepository.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id")  int id) {
        personRepository.deleteById(id);
        return "redirect:/people";
    }

}
