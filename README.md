# ssecurity

## In Memory Authentication

## When Basic Security with properties file, all the end points will be authenticated
    
    spring:
         profiles: basic
         h2:
           console:
             enabled: false
         security:
           user:
             name: user
             password: user

## Basic Security when custom end points needs to be authenticated and authorized

    spring:
      profiles: BasicSecurity
      h2:
        console:
          enabled: false
      security:
        user:
          name: root
          password: root-password
          roles: ADMIN
          
    
    
    package com.arun.ssecurty.config;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Profile;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    
    @EnableWebSecurity
    @Configuration
    @Profile("BasicSecurity")
    public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
    
        private UserConfiguration userConfiguration;
    
        @Autowired
        public BasicSecurityConfig(UserConfiguration userConfiguration) {
            this.userConfiguration = userConfiguration;
        }
    
        /**
         * Authentication
         *
         * @param auth
         * @throws Exception
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser(userConfiguration.getName())
                    .password("{noop}" + userConfiguration.getPassword()).roles(userConfiguration.getRoles());
        }
    
        /**
         * Authorization for specific url
         *
         * @param http
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/v1/**").hasRole(userConfiguration.getRoles())
                    .antMatchers("/v2/**").hasRole(userConfiguration.getRoles())
                    .and()
                    .formLogin()
                    .and()
                    .httpBasic();
        }
    
    }

## Basic Security with custom login page

    spring:
          profiles: BasicSecurity
          h2:
            console:
              enabled: false
          security:
            user:
              name: root
              password: root-password
              roles: ADMIN
              


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
