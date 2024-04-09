package com.msa2024.boards.mapper;

import com.msa2024.boards.BoardVO;
import com.msa2024.boards.PageRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface BoardMapper {
    List<BoardVO> getList(PageRequestVO pageRequestVO);

    int getTotalCount(PageRequestVO pageRequestVO);
    int incViewCount(BoardVO boardVO);
    BoardVO view(BoardVO boardVO);

    int delete(BoardVO boardVO);
    int update(BoardVO boardVO);
    void allDelete();
    int delete(String boardid);
    void insert(BoardVO boardVO);

}