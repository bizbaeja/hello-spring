package com.msa2024.boards;

import com.msa2024.entity.BoardVO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BoardMapper {

	List<BoardVO> getList(PageRequestVO pageRequestVO);
	int  getTotalCount(PageRequestVO pageRequestVO);
	BoardVO view(BoardVO boardVO);
	int incViewCount(BoardVO boardVO);
	int delete(BoardVO boardVO);
	int update(BoardVO boardVO);
	void allDelete();
	int insert(BoardVO boardVO);

}
