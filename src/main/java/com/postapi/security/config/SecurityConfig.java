package com.postapi.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    // Authorization of Roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers(USER_URLS).permitAll().and().authorizeRequests().antMatchers("/skmflsdfdsf/sdfsdnfkj").authenticated().and().httpBasic();
    }

    public static final String[] USER_URLS = {
            "/user/register-email",
            "/user/register-phone",
            "/user/resend-otp",
            "/user/validate-otp",
            "/user/add-information",
            "/user/information",
            "/user/login",
            "/newsfeed/comment/add-comment",
            "/newsfeed/comment/comment",
            "/newsfeed/comment/add-comment",
            "/reply/add-reply",
            "/reply/update-reply",
            "/reply/reply",
            "/reply/reply/{id}",
            "/newsfeed/post-news-feed",
            "/newsfeed/news-feed",
            "/newsfeed/news-feed/{id}",
            "/newsfeed/news-feed"


    };

}
