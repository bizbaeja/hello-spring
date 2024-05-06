package com.msa2024.entity;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestVO {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int pageNo = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String searchKey;

    public int getSkip() {
        return Math.max(0, (pageNo - 1) * size);
    }
    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=").append(pageNo);
        builder.append("&size=").append(size);

        if (searchKey != null && !searchKey.isEmpty()) {
            String encodedSearchKey = URLEncoder.encode(searchKey, StandardCharsets.UTF_8);
            builder.append("&searchKey=").append(encodedSearchKey);
        }
        return builder.toString();
    }
}
