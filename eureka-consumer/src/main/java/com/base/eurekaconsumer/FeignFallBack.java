package com.base.eurekaconsumer;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName FeignFallBack.java
 * @Description TODO
 * @createTime 2019/11/17/ 20:31:00
 */

@Component
public class FeignFallBack implements FeignInterface {

    @Override
    public String postDcRequestBody(Map<String, String> paramMap) {
        return "调用postDcRequestBody失败!";
    }

    @Override
    public String postFeignClientConsumerHystrix() {
        return "调用postFeignClientConsumerHystrix失败!";
    }
}
