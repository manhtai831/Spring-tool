package com.example.spring_demo_app.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    @Column(columnDefinition = "user_name")
    private String userName;
    private String password;
    private String name;
    private String email;
    private Date birth;
    @Column(columnDefinition = "create_at")
    private Date createAt;
    @Column(columnDefinition = "create_by")
    private Long createBy;
    @Column(columnDefinition = "update_at")
    private Date updateAt;
    @Column(columnDefinition = "update_by")
    private Long updateBy;


}
