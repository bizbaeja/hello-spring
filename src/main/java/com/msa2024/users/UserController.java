package com.msa2024.users;

import com.msa2024.boards.BoardVO;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @RequestMapping("loginForm")
    public Object loginForm(BoardVO board, Model model) throws ServletException, IOException {
        log.info("로그인 화면 ");

        return "users/loginForm";
    }
}
