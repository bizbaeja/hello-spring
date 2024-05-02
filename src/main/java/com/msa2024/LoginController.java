package com.msa2024;


import com.msa2024.entity.MemberVO;
import com.msa2024.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	@GetMapping("/users/signupForm")
	public String getSignupView(Model model,
								@RequestParam(value = "error", required = false) String error,
								@RequestParam(value = "exception", required = false) String exception) {
		System.out.println("hello22");
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "users/signupForm";  // Ensure you return the name of the view
	}
//
//	@GetMapping("/users/signup")
//	public String signupForm() {
//		return "users/signupForm"; // 회원가입 폼 페이지 반환
//	}

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
	@PostMapping("/users/signup")
	public String insertUser(@RequestBody MemberVO userVo) { // 회원 가입
		System.out.println("uservo: {}" + userVo);
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



