package com.example.spring_demo_app.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SessionConsolationsInfo {
    public String winning_type;
    public int consolation_start_index;
    public int consolation_end_index;
}
