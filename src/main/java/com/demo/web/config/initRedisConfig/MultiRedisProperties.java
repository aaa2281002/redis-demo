package com.demo.web.config.initRedisConfig;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "spring.redis")
public class MultiRedisProperties {
    /**
     * 默认连接必须配置，配置 key 为 default
     */
    public static final String DEFAULT = "back";

    private boolean enableMulti = false;
    private Map<String, RedisProperties> multi;

    public boolean isEnableMulti() {
        return enableMulti;
    }

    public void setEnableMulti(boolean enableMulti) {
        this.enableMulti = enableMulti;
    }

    public Map<String, RedisProperties> getMulti() {
        return multi;
    }

    public void setMulti(Map<String, RedisProperties> multi) {
        this.multi = multi;
    }
}