package com.example.spring_demo_app.common;

import com.example.spring_demo_app.data.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    static AccountManager accountManager;

    public static AccountManager getInstance() {
        if (accountManager == null) accountManager = new AccountManager();
        return accountManager;
    }

    public List<AccountModel> getAccounts() {
        List<AccountModel> accountModels = new ArrayList<>();
        accountModels.add(new AccountModel("33383334333933343333333533373334333533353336", "346236383666366536373633363836663331", true));
        accountModels.add(new AccountModel("33383334333933373333333533383339333133323336", "346236383666366536373633363836663331"));
        accountModels.add(new AccountModel("33383334333333373338333033343331333533333331", "363836353666363336663665"));
        accountModels.add(new AccountModel("33383334333533383339333433323337333933363339", "333133323333343033313332333336313431"));

        return accountModels;
    }
}
