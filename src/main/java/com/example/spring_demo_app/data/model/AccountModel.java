package com.example.spring_demo_app.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountModel {
    private String phone;

    private Long userid;

    private String password;

    public AccountModel(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String toJson() {
        //          "    \"support_ivs\": true,\n" +
//                "    \"client_identifier\": {\n" +
//                "        \"security_device_fingerprint\": \"JXRorODsKd5nHpnBfcBysw==|XpX6gTpIwn8lxBhl8wcpAe1xagdRy/sydPgURvM9QtE3yi41MyktKi8Nv5nDZdDXihcu98XVNVWUOn0A+XONM5E=|I9JsoazDTEmX1bh5|04|3\"\n" +
//                "    }\n" +
        return "{\n" +
                "    \"phone\": \"" + phone + "\"\n" +
                "    \"password\": \"" + password + "\",\n" +
                "}";
    }
}
