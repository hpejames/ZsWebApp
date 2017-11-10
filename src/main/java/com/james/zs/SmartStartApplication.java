package com.james.zs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 创建整体项目的启动类
 * 启动项目的方式
 * 1.运行此类即可。
 * 2.添加到Tomcat中启动也可以。
 */
@ComponentScan(basePackages={"com.james.zs.web.controller",
                             "com.james.zs.rest.controller",
                             "com.james.zs.exception",
                             "com.james.zs.config",
                             "com.james.zs.service.impl",
                             "com.james.zs.dao"}) // 扫描com.dahan包下所有spring相关组件
@SpringBootApplication // 加载Spring Boot组件
@EnableScheduling // 计划任务执行
public class SmartStartApplication extends SpringBootServletInitializer {
	// 启动项目的主函数
	public static void main(String[] args) {
		SpringApplication.run(SmartStartApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		setRegisterErrorPageFilter(false);
		return application.sources(SmartStartApplication.class);
	}
}
