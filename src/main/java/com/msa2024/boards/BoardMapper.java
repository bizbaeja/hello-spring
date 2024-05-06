package com.msa2024.boards;

import com.msa2024.entity.BoardVO;
import com.msa2024.entity.PageRequestVO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BoardMapper {

	List<BoardVO> getList(PageRequestVO pageRequestVO);
	int  getTotalCount(PageRequestVO pageRequestVO);
	BoardVO view(int boardVO);
	int incViewCount(int boardVO);
	int delete(int boardVO);
	int update(BoardVO boardVO);
	void allDelete();
	int insert(BoardVO boardVO);

	List<BoardVO> getPostsByUserId(int userId);
}
