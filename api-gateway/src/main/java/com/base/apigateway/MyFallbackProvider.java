package com.base.apigateway;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangCheng
 * @title: MyFallbackProvider
 * @projectName api-gateway
 * @description: TODO
 * @date 2019/11/21 14:07
 */

@Component
public class MyFallbackProvider implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        return "eureka-consumer";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse(){
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(("fallback: ===========>: "+MyFallbackProvider.this.getRoute()).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
