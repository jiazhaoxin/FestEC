package com.diabin.latte.app;

import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by admin on 2019/3/8.
 * 配置类 用于项目初始化的一些配置
 */

public class Configurator {

    private static final String TAG = "Configurator";

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();

    //存放字体图标
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    public Configurator() {
        //刚开始初始化 初始化未完成
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
        Log.i(TAG, "Configurator: 配置初始化开始，配置未完成");
    }

    /**
     * 获取实例
     * 此种模式是安全的懒汉模式
     * 静态内部类 + get
     *
     * @return
     */
    public static Configurator getInstance() {
        Log.i(TAG, "getInstance: 获取配置类：此种模式是安全的懒汉模式:静态内部类 + get");
        return Holder.configurator;
    }

    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 静态内部类单例初始化
     * 防止线程冲突
     */
    private static class Holder {
        private static final Configurator configurator = new Configurator();
    }

    final HashMap<String, Object> getConfigs() {
        Log.i(TAG, "getConfigs: 获取配置初始化map");
        return LATTE_CONFIGS;
    }

    /**
     * 配置初始化完成调用此方法
     */
    public final void configure() {
        initIcons();
        Log.i(TAG, "configure: 配置初始化已完成map中设置为true");
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    /**
     * 初始化配置 配置api host
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        Log.i(TAG, "withApiHost: 初始化配置 配置api host");
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED.name(), delayed);
        return this;
    }

    private void initIcons() {
        Log.i(TAG, "initIcons: 初始化字体图标库开始");
        //数组里已经有图标时
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                Iconify.with(ICONS.get(i));
            }
            Log.i(TAG, "initIcons: 初始化字体图标库完成");
        } else {
            Log.i(TAG, "initIcons: 初始化字体图标库未完成");
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        Log.i(TAG, "withIcon: 使用字体图标库：放入array list中");
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 添加请求拦截器
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    /**
     * 检查配置初始化是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 使用配置
     * 根据 key 获取 配置value
     *
     * @param key
     * @param <T>
     * @return
     */
//    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {

        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key.toString());
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
