package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm.entities.User;
import com.scm.scm.forms.UserForm;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.services.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    UserServiceImpl userService;

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
    @RequestMapping( value ="/do-register", method=RequestMethod.POST)
    public String processRegister( @Valid @ModelAttribute UserForm userForm ,BindingResult bindingResult,HttpSession session) {
        System.out.println("Processing Registeration");
        if (bindingResult.hasErrors()) {
            return "register";
        }
        System.out.println(userForm);

        User newUser = new User();
        newUser.setAbout(userForm.getAbout());
        newUser.setEmail(userForm.getEmail());
        newUser.setName(userForm.getName());
        newUser.setPassword(userForm.getPassword());
        newUser.setPhoneNo(userForm.getContactNo());
        userService.saveUser(newUser);
        Message message =  Message.builder().textContent("Registration Successful").type(MessageType.green).build();
       System.out.println(message.getTextContent());
        session.setAttribute("message", message);
        return "redirect:/register";
    }
    
}
