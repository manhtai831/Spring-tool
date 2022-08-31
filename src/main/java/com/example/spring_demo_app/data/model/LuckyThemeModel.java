package com.example.spring_demo_app.data.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LuckyThemeModel {
    private Boolean phone_verified;
    private Boolean is_authenticated;
    private Assets assets;
    private Session session;
    private OnboardingInfo onboarding_info;
    private Boolean is_picking_streak_active;
    private String wss_token;
    private String user_id;
    private String theme_id;
    private String group_id;
    private String source;
    private String theme_type;
    private String ref;
    private String my_number;
    private String session_id;
    private String submit_type;


    public static class Builder {
        private final LuckyThemeModel luckyThemeModel;

        public Builder() {
            luckyThemeModel = new LuckyThemeModel();
        }

        public Builder setThemeId(String themeId) {
            luckyThemeModel.setTheme_id(themeId);
            return this;
        }
        public Builder setGroupId(String groupId) {
            luckyThemeModel.setGroup_id(groupId);
            return this;
        }

        public Builder setSource(String source) {
            luckyThemeModel.setSource(source);
            return this;
        }

        public Builder setNumber(String number) {
            luckyThemeModel.setMy_number(number);
            return this;
        }

        public Builder setSessionId(String sessionId) {
            luckyThemeModel.setSession_id(sessionId);
            return this;
        }

        public Builder setSubmitType(String submitType) {
            luckyThemeModel.setSubmit_type(submitType);
            return this;
        }

        public Builder setRel(String rel) {
            luckyThemeModel.setRef(rel);
            return this;
        }

        public Builder setThemeType(String theme_type) {
            luckyThemeModel.setTheme_type(theme_type);
            return this;
        }

        public LuckyThemeModel build() {
            return luckyThemeModel;
        }
    }
}


