package com.base.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangCheng
 * @title: AccessFilter
 * @projectName api-gateway
 * @description: TODO
 * @date 2019/11/21 15:39
 */
@Component
public class AccessFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("send  {} request to {} ",request.getMethod(),request.getRequestURL().toString());
        String accessToken = request.getParameter("accessToken");
        if(accessToken==null) {
            logger.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("no power");
            return null;
        }
        logger.info("access token ok");
        return null;
    }
}
