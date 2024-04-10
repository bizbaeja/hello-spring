package com.msa2024.users;

import com.msa2024.boards.BoardVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @RequestMapping("loginForm")
    public Object loginForm() throws ServletException, IOException {

        return "users/loginForm";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody UserVO userVO, HttpSession session){

        log.info("삭제 -> {}", userVO);
        //1. 처리
        UserVO loginVO = userService.login(userVO);

        Map<String, Object> map = new HashMap<>();
        if (loginVO != null) { //성공
            session.setAttribute("loginVO", loginVO);
            map.put("loginVO", loginVO);
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "아이디 또는 비밀 번호가 잘못되었습니다");
        }

        return map;
    }
}
