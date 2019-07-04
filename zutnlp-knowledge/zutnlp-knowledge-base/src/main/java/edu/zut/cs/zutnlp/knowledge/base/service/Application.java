package edu.zut.cs.zutnlp.knowledge.base.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.zut.cs")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
