package com.msa2024.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.msa2024.entity.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * MVC 
 * Model : B/L 로직을 구현하는 부분(service + dao)  
 * View  : 출력(jsp) 
 * Controller : model와 view에 대한 제어를 담당 
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CodeService {
	private static final long serialVersionUID = 1L;
      @Autowired(required = true)
	private  CodeMapper  codeMapper;

    public List<CodeVO> getList() {
    	return codeMapper.getList();
	}
}











