package com.msa2024.boards;

import jakarta.servlet.ServletException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController implements Serializable {
    private static final long serialVersionUID = 1L;

    //xml 또는 어노터이션 처리하면 스프링
    //어노터이션 처리하면 스프링 부트
    private final BoardService boardService;
//    private final CodeService codeService;


    @GetMapping("/list")
    //@Valid 추가: Controller 메서드의 파라미터에 @Valid를 추가하여 입력 객체를 검증하도록 할 수 있습니다. 예를 들어, list(BoardVO board, Model model) 메서드에 @Valid를 적용하면,
    // BoardVO 객체가 자동으로 검증됩니다.
    //원래 코드
    public String list(@Valid PageRequestVO pageRequestVO, BindingResult bindingResult, Model model) throws ServletException, IOException {
        log.info("목록");

        log.info(pageRequestVO.toString());

        if(bindingResult.hasErrors()){
            pageRequestVO = PageRequestVO.builder().build();
        }

        //2. jsp출력할 값 설정
        log.info("list {}",boardService.getList(pageRequestVO).getList() );
        model.addAttribute("pageResponseVO", boardService.getList(pageRequestVO));
        //model.addAttribute("sizes", new int[] {10, 20, 50, 100});
//        model.addAttribute("sizes", codeService.getList());
//		model.addAttribute("sizes", "10,20,50,100");

        return "/boards/list";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object>  update(@RequestBody BoardVO board) throws ServletException, IOException {
        log.info("수정 board => {}", board);

        //1. 처리
        int updated = boardService.update(board);

        Map<String, Object> map = new HashMap<>();
        if (updated == 1) { //성공
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "게시물 정보 수정 실패하였습니다");
        }

        return map;
    }
    @GetMapping("/view")
    public String view(BoardVO board, Model model) throws ServletException, IOException {
        log.info("상세보기");

        model.addAttribute("board", boardService.view(board));

        return "/boards/view";
    }
    @GetMapping("updateForm")
    public Object updateForm(BoardVO board, Model model) throws ServletException, IOException {
        System.out.println("수정화면");

        //2. jsp출력할 값 설정
        model.addAttribute("board", boardService.updateForm(board));

        return "boards/updateForm";
    }}
//    @GetMapping(value = "/delete", method = RequestMethod.POST)
//    public String delete(@RequestParam("boardid") String boardid, RedirectAttributes redirectAttributes) {
//        try {
//            int result = boardService.delete(boardid);
//            if (result == 1) {
//                // 삭제 성공 시 메시지 설정
//                redirectAttributes.addFlashAttribute("message", "게시물이 성공적으로 삭제되었습니다.");
//            } else {
//                // 삭제 실패 시 메시지 설정
//                redirectAttributes.addFlashAttribute("message", "게시물 삭제에 실패하였습니다.");
//            }
//        } catch (Exception e) {
//            // 예외 처리
//            redirectAttributes.addFlashAttribute("message", "오류가 발생하여 게시물 삭제에 실패하였습니다.");
//        }
//        return "redirect:/boards/list";
//    }}