package com.demo.web.config.initRedisConfig;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.ReactiveRedisClusterConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * 自定义数据源，实现多数据源切换，利用RedisSelectSupport获取Redis数据源标识，实现多数据源切换
 */
public class MultiRedisJedisConnectionFactory
        implements InitializingBean, DisposableBean, RedisConnectionFactory, ReactiveRedisConnectionFactory {
    private final Map<String, LettuceConnectionFactory> connectionFactoryMap;

    public Map<String, LettuceConnectionFactory> getConnectionFactoryMap() {
        return connectionFactoryMap;
    }

    public MultiRedisJedisConnectionFactory(Map<String, LettuceConnectionFactory> connectionFactoryMap) {
        this.connectionFactoryMap = connectionFactoryMap;
    }

    @Override
    public void destroy() {
        connectionFactoryMap.values().forEach(LettuceConnectionFactory::destroy);
    }

    @Override
    public void afterPropertiesSet() {
        connectionFactoryMap.values().forEach(LettuceConnectionFactory::afterPropertiesSet);
    }

    private LettuceConnectionFactory currentJedisConnectionFactory() {
        String instance = RedisSelectSupport.getSelectInstance();
        if (StringUtils.hasLength(instance)) {
            LettuceConnectionFactory factory = connectionFactoryMap.get(instance);
            return factory;
        }
        return connectionFactoryMap.get(MultiRedisProperties.DEFAULT);
    }

    @Override
    public ReactiveRedisConnection getReactiveConnection() {
        return currentJedisConnectionFactory().getReactiveConnection();
    }

    @Override
    public ReactiveRedisClusterConnection getReactiveClusterConnection() {
        return currentJedisConnectionFactory().getReactiveClusterConnection();
    }

    @Override
    public RedisConnection getConnection() {
        return currentJedisConnectionFactory().getConnection();
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        return currentJedisConnectionFactory().getClusterConnection();
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        return currentJedisConnectionFactory().getConvertPipelineAndTxResults();
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return currentJedisConnectionFactory().getSentinelConnection();
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return currentJedisConnectionFactory().translateExceptionIfPossible(ex);
    }
}