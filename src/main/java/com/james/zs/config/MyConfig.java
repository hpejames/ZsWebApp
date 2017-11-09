package com.james.zs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置
 * @author james.gao
 * @description
 *    因为ConfigurationProperties取消location属性,
 *    所以这里需要使用PropertySource注解
 * @create date 9:49 2017/11/8
 * @modified by james.gao
 * @modify date 9:49 2017/11/8
 * @version v1.0
 */
@Component
@ConfigurationProperties(prefix="test")
@PropertySource("classpath:/config/myConf.properties")
public class MyConfig {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
