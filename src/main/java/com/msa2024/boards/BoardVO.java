package com.msa2024.boards;

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



}