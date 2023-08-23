package com.demo.web.config.initRedisConfig;

/**
 * Redis 切换不同redis数据源操作
 */
public class RedisSelectSupport {

    /**
     * 定义一个静态变量，用于线程间传递值
     */
    private static final ThreadLocal<String> INSTANCE_SELECT_CONTEXT = new ThreadLocal<>();

    public static void selectInstance(String instance) {
        INSTANCE_SELECT_CONTEXT.set(instance);
    }

    public static String getSelectInstance() {
        return INSTANCE_SELECT_CONTEXT.get();
    }

}