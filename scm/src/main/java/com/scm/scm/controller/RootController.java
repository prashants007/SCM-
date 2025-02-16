package com.scm.scm.controller;

import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm.entities.User;
import com.scm.scm.helper.Helper;
import com.scm.scm.services.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    UserService userService;

    @ModelAttribute
    public void addLoggedInUserInfo(Model model,Authentication authentication){
        if(authentication == null){
            return;
        }
       var userName = Helper.getLoggedInUserEmail(authentication);
       User user = userService.getUserByEmail(userName);
        
       if(user != null){
           model.addAttribute("loggedInUser", user);
         }
         else{
             model.addAttribute("loggedInUser", null);
         }
}

}
