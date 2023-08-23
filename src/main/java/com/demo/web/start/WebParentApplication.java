package com.demo.web.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author 龘鵺
 * @ClassName com.demo.web.start
 * @Description 启动类
 * @Date 23/8/2023 上午9:18
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.demo.**")//
@EnableCaching
public class WebParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebParentApplication.class, args);
    }
}
