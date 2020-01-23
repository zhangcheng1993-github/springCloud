package com.base.eurekaconsumer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName restTemplateRibbon.java
 * @Description TODO
 * @createTime 2019/11/16/ 23:13:00
 */
@Configuration
public class RestTemplateConfig {

    @LoadBalanced
    @Bean("restTemplateRibbon")
    public RestTemplate restTemplateRibbon(){
        return new RestTemplate();
    }
}
