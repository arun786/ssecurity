package com.arun.ssecurty.controller;

import com.arun.ssecurty.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @GetMapping("/signup")
    public ModelAndView registrationForm(){
        return new ModelAndView("registerPage", "user", new User());
    }

    @PostMapping("/user/register")
    public ModelAndView registerUser(@Valid final User user){
        System.out.println(user);
        return new ModelAndView("redirect:/login");

    }
}
