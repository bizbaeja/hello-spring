package com.msa2024.boards;

import com.msa2024.entity.BoardFileVO;
import com.msa2024.entity.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {

	// Retrieve all file records associated with a specific board entry
	List<BoardFileVO> getFilesByBoardId(int boardId);


	// Retrieve all file records associated with a specific board entry
	List<BoardFileVO> getList(BoardVO boardVO);

	// Retrieve specific board file information by file ID
	BoardFileVO getBoardFile(int boardFileId);

	// Retrieve the first file information based on board ID
	BoardFileVO getFirstBoardFile(int boardId);

	// Retrieve the first file information based on board ID
	BoardFileVO getBoardFileVO(int boardVO);

	// Delete a board file record
	int delete(BoardFileVO boardFileVO);

	// Insert a new board file record
	int insert(BoardFileVO boardFileVO);
}
