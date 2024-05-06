package com.msa2024.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    private int board_id;          // 게시판 ID
    private String member_id;        // 사용자 ID
    private String title;         // 게시물 제목
    private String content;       // 게시물 내용
    private LocalDateTime created_at;  // 게시물 생성 시간
    private int view_count;        // 조회수
    private String pwd;           // 비밀번호
    private List<BoardFileVO> boardFiles;  // List to store associated files
    private int board_file_id; // 파일 ID 추가
    private BoardFileVO board_file_vo;
    private boolean isNew;
    // 여기서 toString, equals, hashCode, constructor 등이 Lombok에 의해 자동으로 생성됩니다.
    public void setBoardFileVO(BoardFileVO boardFileVO) {
        this.board_file_vo = boardFileVO;
        this.board_file_id = boardFileVO.getBoardFileId(); // 파일 ID 설정
    }
}