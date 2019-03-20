package com.diabin.latte.app;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by admin on 2019/3/8.
 * 加final原因 不想让其他人修改，不能继承
 */

public final class Latte {

    private static final String TAG = "Latte";

    /**
     * 初始化配置方法
     *
     * @param context
     * @return
     */
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER.name());
    }

    public static void test(){
    }
}
