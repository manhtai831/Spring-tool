package com.example.spring_demo_app.data.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OnboardingInfo {
    public String onboarding_banner_image;
    public String onboarding_button_text;
    public String banner_type;
}
