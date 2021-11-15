package com.looseboxes.demo.webapp.ratelimit;

import com.looseboxes.ratelimiter.spring.web.RequestToIdConverter;

import javax.servlet.http.HttpServletRequest;

public class RequestToUtmSourceConverter implements RequestToIdConverter {

    @Override
    public Object convert(HttpServletRequest request) {
        return request.getParameter("utm_source");
    }
}
