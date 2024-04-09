package com.msa2024.boards;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseVO<E> {
	
    private int pageNo;//페이지번호
    private int size;  //페이지의 건수  
    private int total; //전체건수 


    //네비게이션바에서 사용
    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;
    //네비게이션바에서 사용


    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    //목록을 출력할 떄 사용하는 리스트 element
    //객체를 만들 때 여가에 boardVO 나 memberVO 를 넣는다.
    private List<E> list;

    //컨트롤러= > 서비스=> 이걸 만듬 PageResponseVO
    @Builder(builderMethodName = "withAll")
    public PageResponseVO(List<E> list, int total, int pageNo, int size){

        this.pageNo = pageNo;
        this.size = size;

        this.total = total;
        this.list = list;

        this.end =   (int)(Math.ceil(this.pageNo / 10.0 )) * 10;

        this.start = this.end - 9;

        int last =  (int)(Math.ceil((total/(double)size)));

        this.end =  end > last ? last: end;

        this.prev = this.start > 1;

        this.next =  total > this.end * this.size;

    }
}
