package com.czl.xyyq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan("com.czl")
@MapperScan(basePackages = {"com.czl.xyyq.mapper"})
public class XyyqMsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyyqMsBackendApplication.class, args);
    }

}
