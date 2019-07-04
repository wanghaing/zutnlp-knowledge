package edu.zut.cs.zutnlp.knowledge.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages = "edu.zut.cs.zutnlp.knowledge")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
