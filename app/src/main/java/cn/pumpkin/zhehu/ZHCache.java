package cn.pumpkin.zhehu;

import android.content.Context;

public class ZHCache {
    private static Context context;

    private static String account;

    public static void clear() {
        account = null;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        ZHCache.account = account;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ZHCache.context = context.getApplicationContext();
    }

}
