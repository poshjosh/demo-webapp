package com.looseboxes.demo.webapp.ratelimit;

import com.looseboxes.ratelimiter.spring.web.RequestToIdConverter;

import javax.servlet.http.HttpServletRequest;

public class RequestToVMConverter implements RequestToIdConverter {

    @Override
    public Object convert(HttpServletRequest request) {
        return new HttpRequestVM(request);
    }
}
