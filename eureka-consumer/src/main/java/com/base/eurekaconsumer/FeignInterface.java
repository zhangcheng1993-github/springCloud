package com.base.eurekaconsumer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName FeignInterface.java
 * @Description @FeignClient("eureka-client") :请求微服务eureka-client的接口
 * @createTime 2019/11/17/ 19:06:00
 */
@FeignClient(value ="eureka-client",fallback = FeignFallBack.class)
public interface FeignInterface {

    @PostMapping("/postDcRequestBody")
    public String postDcRequestBody(@RequestBody Map<String,String> paramMap);

    @PostMapping("/postFeignClientConsumerHystrix")
    public String postFeignClientConsumerHystrix();


}
