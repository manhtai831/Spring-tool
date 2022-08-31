package com.example.spring_demo_app.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GateModel {
    private Object data;
    private String errorMsg;
    private Integer error;

}
