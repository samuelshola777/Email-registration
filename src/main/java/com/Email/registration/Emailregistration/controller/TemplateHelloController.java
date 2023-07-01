package com.Email.registration.Emailregistration.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateHelloController {

@GetMapping("/hello")
    public String hello(Model model) {
    model.addAttribute("message", "Hello wolrld");
    return "Helloworld goat";
}
@GetMapping("/style")
    public String style(){
   return "add-css-js-demo";


    }
}
