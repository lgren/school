package com.lgren.school.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToView {
    @GetMapping(name = "toActivitiTest")
    public String toActivitiTest() {
        return "activitiTest";
    }

}
