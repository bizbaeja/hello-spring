package com.msa2024.boards;

import com.msa2024.entity.BoardVO;
import com.msa2024.entity.BoardFileVO;
import com.msa2024.entity.PageRequestVO;
import com.msa2024.entity.PageResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private static final String IMAGE_SAVE_DIRECTORY = "/resources/images/";
    @GetMapping("/list")
    public String list(PageRequestVO pageRequestVO, BindingResult bindingResult, Model model) {
        PageResponseVO<BoardVO> boardList = boardService.getList(pageRequestVO); // Assuming the getList method returns a PageResponseVO
        model.addAttribute("boardList", boardList);
        return "boards/list";
    }

    @GetMapping("/{board_id}")
    public String detail(@PathVariable("board_id") int boardId, Model model) {
        System.out.println("board detail");
        Optional<BoardVO> optionalBoard = boardService.view(boardId);
        if (optionalBoard.isPresent()) {
            model.addAttribute("board", optionalBoard.get());
        } else {
            model.addAttribute("message", "Board not found");
            return "redirect:/boards/list";
        }
        return "boards/detail";
    }

    @GetMapping("/create")
    public String createForm() {
        return "boards/create";
    }

    @PostMapping("/create")
    public String create(BoardVO boardVO, RedirectAttributes redirectAttributes) {
        int result = boardService.insert(boardVO);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록되었습니다.");
        return "redirect:/boards/list";
    }


    @PostMapping("/update/{board_id}")
    public String update(@PathVariable("board_id") int board_id, BoardVO boardVO, RedirectAttributes redirectAttributes) {
        boardVO.setBoard_id(board_id);  // Assuming setBno is now setBoardId
        int result = boardService.update(boardVO);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
        return "redirect:/boards/" + board_id;
    }

    @PostMapping("/delete/{board_id}")
    public String delete(@PathVariable("board_id") int boardId, RedirectAttributes redirectAttributes) {
        boardService.delete(boardId);  // Assuming delete now directly accepts boardId
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        return "redirect:/boards/list";
    }

    @PostMapping("/ckUpload")
    @ResponseBody
    public Map<String, Object> ckUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            String fileUrl = boardService.saveCkFileInLocal(file);
            result.put("uploaded", 1);
            result.put("fileName", file.getOriginalFilename());
            result.put("url", request.getContextPath() + "/uploads/" + fileUrl);
        } catch (Exception e) {
            result.put("uploaded", 0);
            result.put("error", "{ 'message': '파일 업로드 중 오류가 발생했습니다.' }");
        }
        return result;
    }

    @GetMapping("/insertForm")
    public String insertform(Model model) {
        return "boards/insertForm";
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertBoard(@RequestParam("title") String title,
                              @RequestParam("password") String password,
                              @RequestParam("content") String content,
                              @RequestParam("file") List<MultipartFile> files,
                              Authentication authentication,
                              RedirectAttributes redirectAttributes) throws IOException  {
        try{
            // Retrieve user details from Spring Security
            String memberId = authentication.getName(); // Assuming username is the memberId
            // System.out.println("멤버Id = " + memberId);

            // Create a BoardVO instance
            BoardVO boardVO = BoardVO.builder()
                    .member_id(memberId)
                    .title(title)
                    .content(content)
                    .pwd(password)
                    .created_at(LocalDateTime.now())
                    .build();

            List<BoardFileVO> fileVOs = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path path = Paths.get("src/main/resources/static/images/" + filename);

                    Files.copy(file.getInputStream(), path);

                    BoardFileVO boardFileVO = BoardFileVO.builder()
                            .originalFilename(file.getOriginalFilename())
                            .realFilename(filename)
                            .contentType(file.getContentType())
                            .size(file.getSize())
                            .makeDate(LocalDateTime.now())
                            .build();
                    fileVOs.add(boardFileVO);
                }
            }
            boardVO.setBoardFiles(fileVOs); // Set the list of files

            int result = boardService.insert(boardVO); // DB 저장

            if (result > 0) {
                return ResponseEntity.ok(Map.of("status", "success", "message", "게시물을 등록하였습니다.", "redirectUrl", "/boards/list"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", "fail", "message", "게시물 등록 실패."));
            }
        }catch (Exception e) {
            log.error("Error during insertion", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", "error", "message", "Internal server error."));
        }
    }

//    @GetMapping("/download/{board_file_id}")
//    public void downloadFile(@PathVariable("board_file_id") int boardFileId, HttpServletResponse response) {
//        BoardFileVO fileVO = boardService.getBoardFile(boardFileId);
//        if (fileVO != null) {
//            boardService.prepareFileDownload(fileVO, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//}
}