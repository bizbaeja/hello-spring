package com.msa2024;


import com.msa2024.boards.BoardService;
import com.msa2024.entity.BoardVO;
import com.msa2024.entity.MemberVO;
import com.msa2024.users.MemberService;
import com.msa2024.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
public class LoginController {
	@Autowired
	UserService userService = null;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BoardService boardService;
	@GetMapping("/users/loginForm")
	public void getLoginView(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {
		System.out.println("hello");
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
	}


	@GetMapping("/checkMemberId")
	@ResponseBody
	public Map<String, String> checkDuplicateMemberId(@RequestParam String memberId) {
		log.info("Checking memberId: {}", memberId);  // 로그 추가
		boolean isDuplicate = memberService.checkDuplicateMemberId(memberId);
		if (isDuplicate) {
			return Map.of("status", "exists");
		} else {
			return Map.of("status", "available");
		}
	}
	@GetMapping("/users/profile")
	public String userProfile(Model model, Principal principal) {
		// 현재 로그인한 사용자의 ID를 가져옵니다.
		String currentUsername = principal.getName();

		// UserService를 통해 MemberVO 객체를 로드합니다.
		MemberVO user = (MemberVO) userService.loadUserByUsername(currentUsername);
		if (user != null) {
			// 사용자의 게시글을 조회합니다.
			List<BoardVO> posts = boardService.getPostsByUserId(user.getMember_id());
			model.addAttribute("user", user);
			model.addAttribute("posts", posts);
		}

		return "users/profile"; // profile.jsp 뷰를 반환합니다.
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



