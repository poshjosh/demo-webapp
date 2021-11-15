package com.looseboxes.demo.webapp.ratelimit;

import com.looseboxes.ratelimiter.spring.web.RateLimiterConfigurer;
import com.looseboxes.ratelimiter.spring.web.RequestToIdConverterRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfigurerImpl implements RateLimiterConfigurer {
    @Override
    public void addConverters(RequestToIdConverterRegistry requestToIdConverterRegistry) {
        requestToIdConverterRegistry.setConverter("general", new RequestToVMConverter());
        requestToIdConverterRegistry.setConverter("utm_source", new RequestToUtmSourceConverter());
    }
}
