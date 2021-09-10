package com.ksy.tom.ver3.goodprojectver3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoodProjectVer3Application {

    public static void main(String[] args) {
        SpringApplication.run(GoodProjectVer3Application.class, args);
    }

}
