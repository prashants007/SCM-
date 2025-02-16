package com.scm.scm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import com.scm.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails userDetails = User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN").build();
    //     var inMemoryUserDetailManager = new InMemoryUserDetailsManager(userDetails);
    //     return inMemoryUserDetailManager;
    // }/

    @Autowired
    SecurityCustomUserDetailService userDetailService;

    @Autowired
    OauthAuthenticationSuccessHandler oauthHandler;

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean 
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider =  new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorizer -> {
            authorizer.requestMatchers("/user/**").authenticated();
            authorizer.anyRequest().permitAll();
        });

        // httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // formLogin.successHandler(null)
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm ->{
            logoutForm.logoutUrl("/do-logout"); 
            logoutForm.logoutSuccessUrl("/login?logout=true");
            // .logoutSuccessHandler((request, response, authentication) -> {
            //     logger.info("auth--->  "+ authentication.getPrincipal().getClass());
                
            // if (authentication != null && authentication instanceof OAuth2AuthenticationToken) {
            //     // Invalidate session and clear context
            //     request.getSession().invalidate();
            //     SecurityContextHolder.clearContext();
            //     logger.info("auth--->  Yessssssssss");

            //     // Redirect to Google's logout endpoint
            //     String googleLogoutUrl = "https://accounts.google.com/Logout";
            //     response.sendRedirect(googleLogoutUrl);
            // } else {
            //     response.sendRedirect("/login?logout=true");
            // }
        // });
        });
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(oauthHandler);
        });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
