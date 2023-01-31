package com.sharsheev.crud.controllers;

import com.sharsheev.crud.dao.PersonDao;
import com.sharsheev.crud.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    private final PersonDao personDao;

    public HomeController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/")
    public List<Person> getPeople() {
        return personDao.index();
    }
}
