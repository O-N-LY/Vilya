package com.stella.service.vilya.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource(value = {
})
@ComponentScan(value = "com.stella.service.vilya.api")
@EnableCaching
public class VilyaApiApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(VilyaApiApplicationContext.class, args);
    }
}
