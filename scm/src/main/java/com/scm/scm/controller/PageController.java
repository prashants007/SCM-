package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.scm.forms.UserForm;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String Home(Model model){
        model.addAttribute("name", "Scm Project");
        model.addAttribute("ProjectDesc", "Smart Contact manager");
        model.addAttribute("gitHubRepo", "https://github.com/prashants007");
        System.out.println("Home page handler");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("This is about page");
        return "about";
    }

    @RequestMapping("/service")
    public String servicePage(){
        System.out.println("This is service page");
        return "service";
    }
    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("This is service page");
        return "contact";
    }
    @RequestMapping("/login")
    public String loginPage(){
        System.out.println("This is service page");
        return "login";
    }
    @RequestMapping("/register")
    public String registerPage(Model model){
        UserForm userForm = new UserForm();
        userForm.setContactNo("3324");
        model.addAttribute("userForm", userForm);
        System.out.println("This is service page");
        return "register";
    }
    @RequestMapping(value ="/do-register", method=RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("Processing Registeration");
        System.out.println(userForm);
        return "redirect:/register";
    }
    
}
