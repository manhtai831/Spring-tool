package com.example.spring_demo_app.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    @NotNull
    @Min(value = 5)
    private String userName;

    private String password;
    private String name;
    private String email;
    private String birth;

    private String createAt;
    private String updateAt;
    private String token;

}
