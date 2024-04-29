package com.msa2024.boards;

import org.apache.ibatis.annotations.Mapper;

import com.msa2024.entity.BoardTokenVO;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardTokenMapper {

	int insert(String board_token);
	int updateStatusComplate(String board_token);
	public List<BoardTokenVO> listToken();
	public int deletes(Map<String, Object> map);
	public void deleteByToken(String token);

}
