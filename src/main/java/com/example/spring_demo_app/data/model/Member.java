package com.example.spring_demo_app.data.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Member{
    public String user_id;
    public String username;
    public String avatar;
    public String number;
    public boolean is_new_player;
}
