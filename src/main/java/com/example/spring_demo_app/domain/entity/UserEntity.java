package com.example.spring_demo_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;
    private String password;
    private String name;
    private String email;
    private Date birth;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "create_by")
    private Long createBy;
    @Column(name = "update_at")
    private Date updateAt;
    @Column(name = "update_by")
    private Long updateBy;

}
