package com.msa2024;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("helloWorld", "안녕하세요");
        return "mainPage";
    }
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("helloWorld", "안녕하세요");
        return "boards/list";
    }
    @GetMapping("/view")
    public String view(Model model){
        model.addAttribute("helloWorld", "안녕하세요");
        return "boards/view";
    }
}
