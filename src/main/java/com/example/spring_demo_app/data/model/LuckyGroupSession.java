package com.example.spring_demo_app.data.model;

import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class LuckyGroupSession {
    public String theme_id;
    public Group group;
    public Session session;
}
