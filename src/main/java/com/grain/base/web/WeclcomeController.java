package com.grain.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeclcomeController {
    @RequestMapping("/welcome")
    public String getWelcomeWeb() {
        return "../../welcome";
    }

}
