package com.base.microserviceconfigclient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName ConfigClientController.java
 * @Description TODO
 * @createTime 2019/11/28/ 21:40:00
 */
@RestController
public class ConfigClientController {

    @Autowired
    private ConfigProperties configProperties;

//    @Value("${encrypt.key}")
//    private String key;

//    @GetMapping("/getKey")
//    public String getKey(){
//        return this.key;
//    }
    @GetMapping("/getKey")
    public String getKey(){
        return configProperties.getKey();
    }
}
