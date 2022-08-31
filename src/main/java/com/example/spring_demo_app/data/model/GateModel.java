package com.example.spring_demo_app.data.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GateModel {
    private String errorMsg;
    private String msg;
    private Integer error;
    private Integer code;
    private Object data;


}
