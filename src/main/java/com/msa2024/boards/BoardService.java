package com.msa2024.boards;

import com.msa2024.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * MVC Architecture:
 * Model: Implements business logic (service + dao)
 * View: Handles presentation (jsp)
 * Controller: Manages interactions between Model and View
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private static final long serialVersionUID = 1L;
    private static final String CURR_IMAGE_REPO_PATH = "/upload";
    private final BoardMapper boardMapper;
    private final BoardFileMapper boardFileMapper;
    private final BoardTokenMapper boardTokenMapper;

    // Date formatting using thread-safe DateTimeFormatter
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("/yyyy/MM/dd");

    public PageResponseVO<BoardVO> getList(PageRequestVO pageRequestVO) {
        log.debug("Fetching list with page: {}, size: {}", pageRequestVO.getPageNo(), pageRequestVO.getSize());
        List<BoardVO> list = boardMapper.getList(pageRequestVO);
        int total = boardMapper.getTotalCount(pageRequestVO);
        log.info("List: {}", list);
        log.info("Total: {}", total);

        return new PageResponseVO<>(list, total, pageRequestVO.getPageNo(), pageRequestVO.getSize());
    }


    public String saveCkFileInLocal(MultipartFile upload) throws IOException {
        if (upload.isEmpty()) {
            return null;
        }
        String fileName = UUID.randomUUID().toString();
        Path saveFilePath = Paths.get(CURR_IMAGE_REPO_PATH, fileName);
        Files.copy(upload.getInputStream(), saveFilePath);

        return saveFilePath.toString();
    }

    int update(BoardVO boardVO) {
        return 0;
    }

    @Transactional
    public Optional<BoardVO> view(int boardId) {
        // 조회수 증가 시도
        int updateCount = boardMapper.incViewCount(boardId);

        // 조회수 증가 실패시 Optional.empty() 반환
        if (updateCount == 0) {
            log.info("Failed to increment view count: Board ID does not exist");
            return Optional.empty();
        }

        // 조회수 증가 성공, 게시글 상세 정보 조회
        BoardVO resultVO = boardMapper.view(boardId);
        if (resultVO == null) {
            log.info("No board found with ID: {}", boardId);
            return Optional.empty();
        }

        // 게시글과 연관된 파일 정보 조회
        List<BoardFileVO> files = boardFileMapper.getFilesByBoardId(boardId);
        resultVO.setBoardFiles(files);

        // 로그 기록
        log.info("게시글과 연관된 파일 정보를 조회하였습니다. 파일 개수: {}", files.size());


        // 로그 정보 기록
        log.info("Viewed Board ID: {}, View Count: {}", boardId, resultVO.getView_count());

        // 게시글 정보와 조회수 업데이트 성공적 반환
        return Optional.of(resultVO);
    }

    @Transactional
    public int insert(BoardVO boardVO) {
        int result = boardMapper.insert(boardVO); // 게시글 정보 저장
        if (boardVO.getBoardFiles() != null && !boardVO.getBoardFiles().isEmpty()) {
            for (BoardFileVO fileVO : boardVO.getBoardFiles()) {
                fileVO.setBoardId(boardVO.getBoard_id()); // BoardFileVO에 BoardVO의 ID 설정
                int fileResult = boardFileMapper.insert(fileVO); // 파일 정보 저장
            }
        }
        return result; // 게시글 저장 결과 반환
    }

    public int delete(int boardId) {
        return boardMapper.delete(boardId);
    }

    public BoardVO updateForm(int boardId) {
        return boardMapper.view(boardId);
    }

    // MultipartFile object saving process
    private Optional<BoardFileVO> writeFile(MultipartFile file) {
        if (file.isEmpty()) {
            return Optional.empty();
        }
        String realFolder = DATE_FORMAT.format(LocalDate.now());
        Path realPath = Paths.get(CURR_IMAGE_REPO_PATH, realFolder);
        if (Files.notExists(realPath)) {
            try {
                Files.createDirectories(realPath);
            } catch (IOException e) {
                log.error("Error creating directory: {}", realPath, e);
                return Optional.empty();
            }
        }
        String fileNameReal = UUID.randomUUID().toString();
        Path realFile = realPath.resolve(fileNameReal);

        try {
            file.transferTo(realFile.toFile());
        } catch (IOException e) {
            log.error("Error saving file: {}", realFile, e);
            return Optional.empty();
        }

        return Optional.of(BoardFileVO.builder()
                .contentType(file.getContentType())
                .originalFilename(file.getOriginalFilename())
                .realFilename(realFile.toString())
                .size(file.getSize())
                .build());
    }

    public BoardFileVO getBoardFile(int boardFileId) {
        return boardFileMapper.getBoardFile(boardFileId);
    }

    public List<BoardVO> getPostsByUserId(String memberId) {
        return boardMapper.getPostsByUserId(memberId);
    }
    // Additional methods implementation like getBoardToken, boardImageFileUpload etc.
}
