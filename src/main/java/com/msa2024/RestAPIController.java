package com.msa2024;

import com.msa2024.entity.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rest_board")
public class RestAPIController {
	
	@GetMapping("/all")
	public ResponseEntity<List<BoardVO>> listBoard() {
		log.info("listBoard 메서드 호출");
		List<BoardVO> list = new ArrayList<BoardVO>();
		for (int i = 0; i < 10; i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(String.valueOf(i));
			vo.setBwriter("이순신"+i);
			vo.setBtitle("안녕하세요"+i);
			vo.setBcontent("새 상품을 소개합니다."+i);
			list.add(vo);
		}
		
		return new ResponseEntity<List<BoardVO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{bno}", method = RequestMethod.GET)
	public ResponseEntity<BoardVO> findBoard (@PathVariable("bno") String bno) {
		log.info("findBoard 메서드 호출");
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		vo.setBwriter("홍길동");
		vo.setBtitle("안녕하세요");
		vo.setBcontent("홍길동 글입니다");
		return new ResponseEntity<BoardVO>(vo, HttpStatus.OK);
	}



	
	//수정하기
	@RequestMapping(value = "/{bno}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBoard (@PathVariable("bno") String bno, @RequestBody BoardVO boardVO) {
		ResponseEntity<String>  resEntity = null;
		try {
			log.info("updateBoard 메서드 호출");
			log.info(boardVO.toString());
			resEntity = new ResponseEntity<String>("MOD_SUCCEEDED",HttpStatus.OK);
		}catch(Exception e) {
			resEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return resEntity;
	}
	
	//삭제하기
	@RequestMapping(value = "/{bno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard (@PathVariable("bno") String bno) {
		ResponseEntity<String>  resEntity = null;
		try {
			log.info("deleteBoard 메서드 호출");
			log.info(bno.toString());
			resEntity = new ResponseEntity<String>("REMOVE_SUCCEEDED",HttpStatus.OK);
		}catch(Exception e) {
			resEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return resEntity;
	}	

		
}
