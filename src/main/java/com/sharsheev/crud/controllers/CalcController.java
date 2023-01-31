package com.sharsheev.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalcController {
   @GetMapping("/eval")
    public String evaluate(@RequestParam("a") int a, @RequestParam("b") int b,
                           @RequestParam("action") String action, Model model) {
        double result = 10d;
        switch (action) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "/" -> result = (double) a / b;
            case "*" -> result = a * b;
        }
        model.addAttribute("result",result);
        return "/calculator";
    }
}
