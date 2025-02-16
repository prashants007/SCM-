package com.scm.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.helper.Helper;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        logger.info("Logged in with user "+Helper.getLoggedInUserEmail(authentication));
        return "user/profile";
    }
    

}
