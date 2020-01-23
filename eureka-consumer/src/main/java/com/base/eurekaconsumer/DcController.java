package com.base.eurekaconsumer;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zc
 * @version 1.0.0
 * @ClassName DcController.java
 * @Description TODO
 * @createTime 2019/11/16/ 10:40:00
 */
@RestController
public class DcController {


    @Autowired
    public LoadBalancerClient loadBalancerClient;

    @Autowired
    @Qualifier("restTemplateRibbon")
    public RestTemplate restTemplateRibbon;

    @Autowired
    public FeignInterface feignInterface;



    /**
     * get请求(rest风格)
     * @return
     */
    @GetMapping("/getPathVariableConsumer")
    public String getPathVariableConsumer(){

        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> paramMap = new HashMap();
        paramMap.put("start","1");
        paramMap.put("page","5");
        return restTemplate.getForObject("http://localhost:8001/getDcPathVariable/{start}/{page}",String.class,paramMap);

    }


    /**
     * get请求
     * @return
     */
    @GetMapping("/getConsumer")
    public String getConsumer(){

      RestTemplate restTemplate=new RestTemplate();
      Map<String,String> paramMap = new HashMap();
      paramMap.put("start","1");
      paramMap.put("page","5");
      return restTemplate.getForObject("http://localhost:8001/getDc?start={start}&page={page}",String.class,paramMap);
    }



    /**
     * post请求
     * @return
     */
    @GetMapping("/postConsumer")
    public String postConsumer(){

        RestTemplate restTemplate=new RestTemplate();
        //post请求
        MultiValueMap<String, String> paramMap= new LinkedMultiValueMap<String, String>();
        paramMap.add("start", "1");
        paramMap.add("page", "5");
        return restTemplate.postForObject("http://localhost:8001/postDc",paramMap,String.class);
    }



    /**
     * post请求(RequestBody)
     * @return
     */
    @GetMapping("/postRequestBodyConsumer")
    public String postRequestBodyConsumer(){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> paramMap = new HashMap();
        paramMap.put("start", "1");
        paramMap.put("page", "5");
        return restTemplate.postForObject("http://localhost:8001/postDcRequestBody",paramMap,String.class);
    }



    /**
     * post请求(loadBalancerClient)
     * @return
     */
    @GetMapping("/postBalancerConsumer")
    public String postBalancerConsumer(){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> paramMap = new HashMap();
        paramMap.put("start", "1");
        paramMap.put("page", "5");
        //选中服务实例
        ServiceInstance serviceInstance =loadBalancerClient.choose("eureka-client");
        System.out.println(serviceInstance.getServiceId()+":"+serviceInstance.getPort());
        String url="http://"+serviceInstance.getHost()+ ":" + serviceInstance.getPort() + "/postDcRequestBody";;

        return restTemplate.postForObject(url,paramMap,String.class);
    }



    /**
     * post请求(loadBalancerClient)
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/postRestTemplateRibbonConsumer")
    public String postRestTemplateRibbonConsumer(){
        Map<String,String> paramMap = new HashMap();
        paramMap.put("start", "1");
        paramMap.put("page", "5");
        return this.restTemplateRibbon.postForObject("http://eureka-client/"+"/postDcRequestBody",paramMap,String.class);
    }



    public String fallback() {
        return "fallback";
    }


    /**
     * post请求(Feign接口)
     * @return
     */
    @GetMapping("/postFeignClientConsumer")
    public String postFeignClientConsumer(){
        Map<String,String> paramMap = new HashMap();
        paramMap.put("start", "1");
        paramMap.put("page", "5");
        return this.feignInterface.postDcRequestBody(paramMap);
    }



    /**
     * post请求(Feign接口)
     * @return
     */
    @GetMapping("/postFeignClientConsumerHystrix")
    public String postFeignClientConsumerHystrix(){
        return this.feignInterface.postFeignClientConsumerHystrix();
    }

}
