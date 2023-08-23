package com.demo.web.config.initRedisConfig;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(-99999)
public class RedisInstanceSelectAspect {

//    private final String defaultInstance = MultiRedisProperties.DEFAULT;
//    private RedissonClient redissonClient;

//    @Autowired
//    public void setRedissonClient(RedissonClient redissonClient) {
//        this.redissonClient = redissonClient;
//    }

//    /**
//     * 创建RedisSelect对应的切面，来对标有注解的方法拦截
//     *
//     * @param point
//     * @return
//     * @throws Throwable
//     */
//    @Around("@annotation(com.framework.common.annotation.RedisSelect)")
//    @ConditionalOnBean(MultiRedisJedisConnectionFactory.class)
//    public Object configRedis(ProceedingJoinPoint point) throws Throwable {
//        String instance = null;
//        try {
//            MethodSignature signature = (MethodSignature) point.getSignature();
//            Method method = signature.getMethod();
//
//            RedisSelect config = method.getAnnotation(RedisSelect.class);
//            if (config != null) {
//                instance = config.instance();
//            }
//            RedisSelectSupport.selectInstance(instance);//切换redis数据源
//            return point.proceed();
//        } finally {
//            RedisSelectSupport.selectInstance(defaultInstance);
//        }
//    }

    ///@Before是在所拦截方法执行之前执行一段逻辑。
    // @After 是在所拦截方法执行之后执行一段逻辑。
    // @Around是可以同时在所拦截方法的前后执行一段逻辑。+
    // 作为@Pointcut的参数，用以定义连接点

    /**
     * @titel 配置切面目录
     * @description 配置切面目录
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
//    @Pointcut("execution(* com.framework.web.controller..*.*(..))")
    @Pointcut("execution(* com.demo.service..*.*(..))")
    public void pointcut() {
    }

    /**
     * @param rs 1 数据源注解
     * @titel 执行方法前更换数据源
     * @description 执行方法前更换数据源
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @Before("pointcut() && @annotation(rs)")
    public void beforeDataSource(RedisSelect rs) {
//        String instance = null;
//        try {
//            MethodSignature signature = (MethodSignature) point.getSignature();
//            Method method = signature.getMethod();
//
//            RedisSelect config = method.getAnnotation(RedisSelect.class);
//            if (config != null) {
//                instance = config.instance();
//            }
//            RedisSelectSupport.selectInstance(instance);//切换redis数据源
//            return point.proceed();
//        } finally {
//            RedisSelectSupport.selectInstance(defaultInstance);
//        }
        String value = rs.instance();
        RedisSelectSupport.selectInstance(value);//切换redis数据源
    }

    /**
     * @param rs 1 数据源注解
     * @titel 执行方法后清除数据源设置
     * @description 执行方法后清除数据源设置
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @After("pointcut() && @annotation(rs)")
    public void afterDataSource(RedisSelect rs) {
        RedisSelectSupport.selectInstance(null);
    }
}