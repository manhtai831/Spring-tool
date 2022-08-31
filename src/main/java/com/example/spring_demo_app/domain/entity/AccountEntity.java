package com.example.spring_demo_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity {
    @Id
    private String id;

    @Field("user_id")
    private Long userid;

    private String phone;

    private String password;

    public AccountEntity(Long userid) {
        this.userid = userid;
    }
}
