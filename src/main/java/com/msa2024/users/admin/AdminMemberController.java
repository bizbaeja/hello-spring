package com.msa2024.users.admin;

import com.msa2024.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Or whatever your package structure is

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/admin/member") // Adjusted from "/admin/member" to "/users/admin/member"
public class AdminMemberController {

	private final AdminMemberService memberService;

	@GetMapping("/list")
	public String listMembers(Model model) {
		List<MemberVO> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "users/admin/member/list"; // Adjusted view path
	}

	@PostMapping("/lockunlock")
	public String lockUnlockMembers(@RequestParam List<String> memberIds, RedirectAttributes redirectAttributes) {
		boolean success = memberService.lockUnlockMembers(memberIds);
		if (success) {
			redirectAttributes.addFlashAttribute("successMessage", "Selected accounts have been updated.");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "There was a problem updating the accounts.");
		}
		return "redirect:/users/admin/member/list";
	}

	@PostMapping("/delete")
	public String deleteMembers(@RequestParam List<String> memberIds, RedirectAttributes redirectAttributes) {
		boolean success = memberService.deleteMembers(memberIds);
		if (success) {
			redirectAttributes.addFlashAttribute("successMessage", "Selected accounts have been deleted.");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "There was a problem deleting the accounts.");
		}
		return "redirect:/users/admin/member/list";
	}

}






