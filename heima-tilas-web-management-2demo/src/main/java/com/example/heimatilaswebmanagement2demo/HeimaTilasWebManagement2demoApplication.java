package com.example.heimatilaswebmanagement2demo;

import com.example.EnableHeaderConfig;
import com.example.HeaderConfig;
import com.example.TokenParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@EnableHeaderConfig
@SpringBootApplication
public class HeimaTilasWebManagement2demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeimaTilasWebManagement2demoApplication.class, args);
    }

}
