package com.demo.web.config.initRedisConfig;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@ConditionalOnProperty(prefix = "spring.redis", value = "enable-multi", matchIfMissing = false)
@Configuration(proxyBeanMethods = false)
public class RedisInstanceConfig {
    @Bean
    public MultiRedisJedisConnectionFactory multiRedisJedisConnectionFactory(
                                                                             MultiRedisProperties multiRedisProperties
//                                                                            , ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers,
//                                                                             ClientResources clientResources,
//                                                                             ObjectProvider<RedisSentinelConfiguration> sentinelConfigurationProvider,
//                                                                             ObjectProvider<RedisClusterConfiguration> clusterConfigurationProvider
    ) {
        //读取配置
        Map<String, LettuceConnectionFactory> connectionFactoryMap = new HashMap<>();
        Map<String, RedisProperties> multi = multiRedisProperties.getMulti();
        multi.forEach((k, v) -> {
            LettuceConnectionFactory JedisConnectionFactory = JedisConnectionFactory(v);
            connectionFactoryMap.put(k, JedisConnectionFactory);
        });
        return new MultiRedisJedisConnectionFactory(connectionFactoryMap);
    }

    public LettuceConnectionFactory JedisConnectionFactory(RedisProperties redisProperties) {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        // 设置选用的数据库号码
        redisConfiguration.setDatabase(redisProperties.getDatabase());
        // 设置 redis 数据库密码
        redisConfiguration.setPassword(redisProperties.getPassword());
        // 根据配置和客户端配置创建连接
        LettuceConnectionFactory factory = new LettuceConnectionFactory( redisConfiguration);

        return factory;
    }

}