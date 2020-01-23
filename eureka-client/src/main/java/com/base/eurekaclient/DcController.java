package com.base.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName DcController.java
 * @Description TODO
 * @createTime 2019/11/16/ 09:34:00
 */
@RestController
public class DcController {

    //DiscoveryClient:实例对象
    @Autowired
    public DiscoveryClient discoveryClient;

    @GetMapping("/getDc")
    public String getDc(@RequestParam("start")  String start,@RequestParam("page")  String page){

        //获取本客户端的服务缓存清单
        String services = "Services: " + discoveryClient.getServices()+",参数page="+page+",start="+start;
        System.out.println(services);
        return  services;
    }


    @GetMapping("/getDcPathVariable/{start}/{page}")
    public String getDcPathVariable(@PathVariable("start") String start,@PathVariable("page") String page){

        //获取本客户端的服务缓存清单
        String services = "Services: " + discoveryClient.getServices()+",参数page="+page+",start="+start;
        System.out.println(services);
        return  services;
    }



    @PostMapping("/postDc")
    public String postDc(@RequestParam("start") String start,@RequestParam("page")  String page){

        //获取本客户端的服务缓存清单
        String services = "Services: " + discoveryClient.getServices()+",参数page="+page+",start="+start;
        System.out.println(services);
        return  services;
    }



    @PostMapping("/postDcRequestBody")
    public String postDc(@RequestBody Map<String,String> paramMap, HttpServletRequest request){

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取本客户端的服务缓存清单
        String services = "Services: " + discoveryClient.getServices()+",参数page="+paramMap.get("page")+",start="+paramMap.get("start")+"请求url:"+request.getRequestURL().toString();
        return  services;
    }

    @PostMapping("/postFeignClientConsumerHystrix")
    public String postFeignClientConsumerHystrix(){

        //获取本客户端的服务缓存清单
        return  "345";
    }


}
