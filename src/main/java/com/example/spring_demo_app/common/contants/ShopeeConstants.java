package com.example.spring_demo_app.common.contants;

public class ShopeeConstants {


    public static class ShopeeUrl {
        public static final String BASE_URL = "https://shopee.vn";
        public static final String SO_GI_DAY = "https://giaitri.shopee.vn";
    }

    public static class ShopeeAuth {
        public static final String LOGIN = "/api/v4/account/login_by_password";
    }

    public static class ShopeeMkt {
        public static final String COIN = "/mkt/coins/api/v2/checkin_new";
    }
    public static class ShopeeLottery {
        public static final String THEME_INFO = "/gc-api/desktop-app-api/lottery/get-info/";
        public static final String PICK_NUMBER = "/gc-api/desktop-app-api/lottery/pick/";
        public static final String CREATE_GROUP = "/gc-api/desktop-app-api/lottery/create-group/";
        public static final String JOIN_GROUP = "/gc-api/desktop-app-api/lottery/join-group/";
        public static final String CLAIM_REWARD = "/gc-api/desktop-app-api/lottery/picking-streak/claim-rewards/";
        public static final String CREATE_GROUP_LINK = "/gc-api/desktop-app-api/lottery/create-group-link/";
        public static final String GET_GROUP_BY_REL= "/gc-api/desktop-app-api/lottery/get-group-by-ref/";
    }
}
