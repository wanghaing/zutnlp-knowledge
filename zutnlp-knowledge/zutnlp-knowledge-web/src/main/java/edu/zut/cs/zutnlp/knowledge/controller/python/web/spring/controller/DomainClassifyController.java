//package edu.zut.cs.zutnlp.knowledge.controller.python.web.spring.controller;
//
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//
//
//@RestController
//public class DomainClassifyController {
//
//    @GetMapping("/classification")
//    public String Resource() {
//
//        URL url;
//
//        {
//            try {
//                url = new URL("https://localhost:15000/classification");
//                InputStream is = url.openStream();
//                InputStreamReader isr = new InputStreamReader(is, "utf-8");
//                BufferedReader br = new BufferedReader(isr);
//
//                String res = null;
//                String line ;
//                while ((line = br.readLine()) != null) {
//                    res += line;
//                }
//                br.close();
//                is.close();
//                isr.close();
//                return res;
//            } catch (java.io.IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//}