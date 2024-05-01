package com.msa2024.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.msa2024.entity.MemberVO;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(com.msa2024.users.UserController.class);

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String loginPage() { // 로그인되지 않은 상태이면 로그인 페이지를, 로그인된 상태이면 home 페이지를 보여줌
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "/users/loginForm";
        return "redirect:/";
    }

    @GetMapping("/users/loginForm")
    public String loginForm(){
        return "/users/loginForm";
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/users/signupForm")
    public String getSignupView(Model model,
                                @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "exception", required = false) String exception) {
        System.out.println("hello");
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "users/signupForm";  // Ensure you return the name of the view
    }



    @GetMapping("/users")
    public void temp(){

    }

    @GetMapping("/users/userList")
    public String getUserList(Model model) { // User 테이블의 전체 정보를 보여줌
        List<MemberVO> userList = userService.getUserList();
        model.addAttribute("list", userList);
        return "users/userListPage";
    }

    @GetMapping("/users/signup")
    public String signupPage() {  // 회원 가입 페이지
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "/";
        return "redirect:/";
    }
    @PostMapping("/users/signup")
    public String insertUser(MemberVO userVo) { // 회원 가입
        try {
            userService.insertUser(userVo);
        } catch (DuplicateKeyException e) {
            return "redirect:/signup?error_code=-1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/signup?error_code=-99";
        }
        return "redirect:/login";
    }
}

