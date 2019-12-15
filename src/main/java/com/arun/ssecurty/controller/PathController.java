package com.arun.ssecurty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

    @RequestMapping("/login")
    public String lsi() {
        return "/loginPage";
    }
}
