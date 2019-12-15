package com.arun.ssecurty.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "spring.security.user")
@Getter
@Setter
public class UserConfiguration {
    private String name;
    private String password;
    private String roles;
}
