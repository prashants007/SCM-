package com.scm.scm.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.var;

public class Helper {

    public static String getLoggedInUserEmail(Authentication authentication){
        if(authentication instanceof OAuth2AuthenticationToken){
                var auth2User = (OAuth2User)authentication.getPrincipal();
                String email = auth2User.getAttribute("email");
                return email;
        }
        else{
            return authentication.getName();
        }
    }
}
