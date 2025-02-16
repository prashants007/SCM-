package com.scm.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm.entities.Providers;
import com.scm.scm.entities.User;
import com.scm.scm.helper.AppConstants;
import com.scm.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(OauthAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                logger.info("Oauth Authentication Success");

                var oAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;

                String authorisedClientRegId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
                logger.info(authorisedClientRegId);
                // if(authorisedClientRegId.equals("google")){

                // }
                // else if(authorisedClientRegId.equals("github")){

                // }
                DefaultOAuth2User auth2User = (DefaultOAuth2User)authentication.getPrincipal();
                logger.info(auth2User.toString());
                String email = auth2User.getAttribute("email").toString();
                String name = auth2User.getAttribute("name").toString();
                String profilePicUrl = auth2User.getAttribute("picture").toString();

                if(!userRepo.findByEmail(email).isPresent()){
                User user = new User();
                user.setEmail(email);
                user.setProfiePic(profilePicUrl);
                user.setName(name);
                user.setAbout("This is google login");
                user.setEmailVerified(true);
                user.setPassword("password");
                user.setEnabled(true);
                user.setProviderUserId(auth2User.getName());
                user.setProviders(Providers.GOOGLE);
                user.setRoleList(List.of(AppConstants.USER_ROLE));
                user.setUserId(UUID.randomUUID().toString());
                userRepo.save(user);
            }
            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
