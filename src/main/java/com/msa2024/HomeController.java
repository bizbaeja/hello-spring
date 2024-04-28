package com.msa2024;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value={"/", "/a"})
    public String HomeController(){
        return "index";
    }


}
