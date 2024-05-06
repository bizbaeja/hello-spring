package com.msa2024.users;

import com.msa2024.boards.BoardService;
import com.msa2024.entity.BoardVO;
import com.msa2024.entity.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/profile")
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

}