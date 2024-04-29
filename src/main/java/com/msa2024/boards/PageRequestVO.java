package com.msa2024.boards;




import com.msa2024.entity.BoardVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
@Builder
@Data
@AllArgsConstructor //모든 인자를 다 전달해준다.
@NoArgsConstructor
public class PageRequestVO {
    //validationCheck 용도
    @Builder.Default
    @Min(value = 1)
    @Positive //양수 pom.xml
    private int pageNo = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String link; //get 방식으로 데이터를 설정할 용도였지만 안씀

    private String searchKey;

    public int getSkip() {
        return (pageNo - 1) * size;
    }

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.pageNo);
            builder.append("&size=" + this.size);

            //검색어가 존재할 경우
            if (this.searchKey != null && this.searchKey.length() != 0) {
                try {
                    builder.append("&searchKey=" + URLEncoder.encode(this.searchKey, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            link = builder.toString();
        }
        return link;
    }

}