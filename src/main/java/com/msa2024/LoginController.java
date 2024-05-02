package com.msa2024;


import com.msa2024.entity.MemberVO;
import com.msa2024.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	UserService userService = null;
	@GetMapping("/users/loginForm")
	public void getLoginView(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {
		System.out.println("hello");
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
	}

	@GetMapping("/users")
	public void temp(){

	}


	@GetMapping("/login")
	public String login() {
		return "/users/loginForm";  // 로그인 페이지로의 경로를 반환
	}
	@GetMapping("/users/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
