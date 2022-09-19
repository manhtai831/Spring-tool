package com.example.spring_demo_app.data.model;

import com.example.spring_demo_app.common.utils.HashSecurity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AccountModel {
    private String phone;

    private Long userid;

    private String password;

    private Boolean isLeader;

    public AccountModel(String phone, String password) {
        this.phone = phone;
        this.password = password;
        this.isLeader = false;
    }

    public AccountModel(String phone, String password, Boolean isLeader) {
        this.phone = phone;
        this.password = password;
        this.isLeader = isLeader;
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

    public String getPhone() {
        return HashSecurity.unHash(HashSecurity.unHash(phone));
    }

    public Long getUserid() {
        return userid;
    }

    public String getPassword() {
        return HashSecurity.unHash(HashSecurity.unHash(password));
    }

    public Boolean getLeader() {
        return isLeader;
    }
}
