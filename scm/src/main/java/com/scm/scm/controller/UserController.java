package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path = "/user")
public class UserController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
    

}
