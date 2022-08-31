package com.example.spring_demo_app.data.model;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class Group {
    public String group_id;
    public String owner_user_id;
    public String owner_username;
    public String owner_avatar;
    public Boolean is_member;
    public ArrayList<Member> members;
}
