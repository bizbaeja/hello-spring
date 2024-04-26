package com.msa2024.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    private int boardid;
    private String title;
    private String content;
    private String userid;
    private String bwriter;
    private String viewCount;
    private String postdate;

    //검색키
    private String searchKey;

    public BoardVO(int boardid, String title, String content, String userid, String viewCount) {
        this.boardid = boardid;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.viewCount = viewCount;
    }
}
