package com.dbalthassat.controller;

import com.dbalthassat.dto.Event;
import com.dbalthassat.dto.Person;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/error")
public class ErrorController {
    public String error() {
        return "errourrror";
    }
}
