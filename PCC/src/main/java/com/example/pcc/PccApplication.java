package com.example.pcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class PccApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(PccApplication.class, args);
    }

}
