package com.msa2024.code;

import org.apache.ibatis.annotations.Mapper;
import com.msa2024.entity.CodeVO;

import java.util.List;

@Mapper
public interface CodeMapper {

	List<CodeVO> getList();
}
