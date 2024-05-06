package com.msa2024.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardFileVO {
    private int boardFileId;       // 파일 ID
    private int boardId;           // 연결된 게시글 ID
    private String originalFilename; // 원본 파일명
    private String realFilename;     // 서버에 저장된 실제 파일명
    private String contentType;      // 파일의 MIME 타입
    private long size;               // 파일 크기
    private LocalDateTime makeDate;  // 파일이 생성된 날짜와 시간

    // 여기서 toString, equals, hashCode, constructor 등이 Lombok에 의해 자동으로 생성됩니다.

    public void generateFileName() {
        this.realFilename = UUID.randomUUID().toString() + "_" + this.originalFilename;
    }
}