package com.example.spring_demo_app.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseList<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalCurrent;
    private Long totalElement;
    private List<T> data;


//    public static <T> ResponseList<T> list(Integer total,List<T> data) {
//        return new ResponseList<T>(total, data);
//    }

    public static <T> BaseResponse ok(Page<T> data) {
        return BaseResponse.success(new ResponseList<T>(data.getNumber(), data.getSize(), data.getTotalPages(), data.getContent().size(), data.getTotalElements(), data.getContent()));
    }


}
