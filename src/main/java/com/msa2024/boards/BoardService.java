package com.msa2024.boards;


import com.msa2024.boards.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class BoardService {
    private static final long serialVersionUID = 1L;

    private final BoardMapper  boardMapper;

    public PageResponseVO<BoardVO> getList(PageRequestVO pageRequestVO) {
        List<BoardVO> list = boardMapper.getList(pageRequestVO);
        int total = boardMapper.getTotalCount(pageRequestVO);

        log.info("list {} ", list);
        log.info("total  = {} ", total);

        PageResponseVO<BoardVO> pageResponseVO = PageResponseVO.<BoardVO>withAll()
                .list(list)
                .total(total)
                .size(pageRequestVO.getSize())
                .pageNo(pageRequestVO.getPageNo())
                .build();

        return pageResponseVO;
    }

    public BoardVO view(BoardVO board)  {

        if (0 == boardMapper.incViewCount(board)) {
            return null;
        }
        BoardVO resultVO = boardMapper.view(board);
        log.info(resultVO.getViewCount());
        log.info(resultVO.toString());
        return resultVO;
    }

    public int update(BoardVO board) {
        return boardMapper.update(board);
    }
    //
    public int delete(String boardid) {
        return boardMapper.delete(boardid);
    }
    public int boardForm(BoardVO board) {
        return boardMapper.insert(board);
    }
    public int insert(BoardVO board)  {
        return boardMapper.insert(board);
    }
    public BoardVO updateForm(BoardVO board)  {
        return boardMapper.view(board);
    }

}
