package com.base.microserviceconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName ConfigProperties.java
 * @Description TODO
 * @createTime 2019/11/28/ 22:05:00
 */
@Component
public class ConfigProperties {

    @Value("${encrypt.key}")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
