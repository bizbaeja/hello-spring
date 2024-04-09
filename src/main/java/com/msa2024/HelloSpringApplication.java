package com.msa2024;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.msa2024.boards.mapper")
public class HelloSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }
}
