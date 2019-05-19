package com.example.london.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Biliaiev
 * Created on 19/05/2019.
 */
@Controller
public class AdminController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
