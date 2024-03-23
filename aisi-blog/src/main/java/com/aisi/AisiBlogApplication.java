package com.aisi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 8:10
 * @Description:
 */

@SpringBootApplication
@MapperScan("com.aisi.mapper")
public class AisiBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AisiBlogApplication.class, args);
    }
}
