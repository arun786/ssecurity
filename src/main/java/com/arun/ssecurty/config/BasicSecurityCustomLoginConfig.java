package com.arun.ssecurty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile(value = "BasicSecurityCustomLoginPage")
@Configuration
@EnableWebSecurity
public class BasicSecurityCustomLoginConfig extends WebSecurityConfigurerAdapter {

    private UserConfiguration userConfiguration;

    @Autowired
    public BasicSecurityCustomLoginConfig(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    //authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(userConfiguration.getName())
                .password("{noop}"+userConfiguration.getPassword())
                .roles(userConfiguration.getRoles());
    }

    //authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/**").hasRole(userConfiguration.getRoles())
                .and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/doLogin");
    }
}
