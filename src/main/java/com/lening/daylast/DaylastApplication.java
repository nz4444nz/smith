package com.lening.daylast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.lening.daylast.mapper"})
public class DaylastApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaylastApplication.class,args);
    }
}
