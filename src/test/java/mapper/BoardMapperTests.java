package mapper;

import com.msa2024.boards.BoardMapper;
import com.msa2024.entity.BoardVO;
import com.msa2024.entity.PageRequestVO;
import com.msa2024.entity.PageResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {

    /**
     *
     */
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testClearAll() {
    	boardMapper.allDelete();
    }

    @Test
    public void testCreateSampleData() {
    	for (int i=0;i<200;i++) {
	    	BoardVO boardVO = BoardVO.builder()
	    			.board_id(i)
	    			.title("게시물 내용 " + i)
                    .content("rp")
	    			.build();
	    	
	    	if (i % 2 == 0) {
	    		boardVO.setMember_id("msa1");
	    	} else {
	    		boardVO.setMember_id("msa2");
	    	}
	    	
	    	boardMapper.insert(boardVO);
    	}
    }
    
    @Test 
    public void testPage() {
        PageRequestVO pageRequestVO = PageRequestVO.builder().pageNo(1).size(10).build();
    	List<BoardVO> list = boardMapper.getList(pageRequestVO);
        int total = boardMapper.getTotalCount(pageRequestVO);
        
//        log.info("list {} ", list);
//        log.info("total  = {} ", total);

        PageResponseVO<BoardVO> pageResponseVO = PageResponseVO.<BoardVO>withAll()
                .list(list)
                 .total(total)
                .size(pageRequestVO.getSize())
                .pageNo(pageRequestVO.getPageNo())
                .build();
//
//        log.info("pageResponseVO  = {} ", pageResponseVO);

    }
}

