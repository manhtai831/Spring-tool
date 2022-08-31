package com.example.spring_demo_app.common;

import com.example.spring_demo_app.data.model.LuckyGroupSession;
import com.example.spring_demo_app.data.model.LuckyThemeModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupStored {

    static GroupStored groupStored;
    private String rel;
    private LuckyGroupSession groupSession;
    private LuckyThemeModel luckyThemeModel;

    public static GroupStored getInstance() {
        if (groupStored == null) groupStored = new GroupStored();
        return groupStored;
    }


}
