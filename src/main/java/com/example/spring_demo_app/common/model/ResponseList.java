package com.example.spring_demo_app.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseList<T> {
//    private Long pageIndex;
//    private Long pageSize;
    private Integer total;
    private List<T> data;


    public static <T> ResponseList<T> list(Integer total,List<T> data) {
        return new ResponseList<>(total, data);
    }

    public static <T> BaseResponse ok(Integer total,List<T> data) {
        return BaseResponse.success(new ResponseList<>(total, data));
    }


}
