package com.msa2024;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CalculateController {
    @GetMapping("/addForm")
    public String addForm(){
       return "addForm";
    }

    @PostMapping("/add")
    public  String addNumbers(int a, int b, Model model){
        int result = a + b;
        model.addAttribute("result", result);
        return "result";
    }
    @PostMapping("jsonCalc")
    @ResponseBody
    public Map<String, Object> jsonCalc(@RequestBody Map<String, Integer> map){
        Map<String, Object> result1 = new HashMap<>();

        result1.put("status", 0);
        result1.put("result", map.get("a") + map.get("b"));

        return result1;
    }
//    @PostMapping("/add")
//    public String addNumbers(@RequestParam("a") int a ,@RequestParam("b") int b, Model model) {
//        int result = a + b;
//        model.addAttribute("result",result);
//        return "result";
//    }
//    @RequestParam("a") int a ,@RequestParam("b") 대신에 그냥, int a, int b 를 쓰고,
//    result.jsp  에서 ${result} = ${param.a} + ${param.b} 로 바꿀 수 있다.
}
