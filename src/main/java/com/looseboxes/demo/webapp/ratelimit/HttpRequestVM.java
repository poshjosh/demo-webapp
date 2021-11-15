package com.looseboxes.demo.webapp.ratelimit;

import com.looseboxes.spring.webapp.util.WebUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class HttpRequestVM {

    private String sessionId;
    private String ipAddress;

    public HttpRequestVM(HttpServletRequest request) {
        this(
                // To prevent the arbitrary creation of HttpSessions via HttpServletRequest.getSession we use
                // a utility method which first tries to access the HttpSession via the RequestContextHolder
                WebUtil.getSessionOptional(request)
                        .map(session -> session.getId()).orElse(null),
                WebUtil.getClientIpAddress(request, ""));
    }

    public HttpRequestVM(String sessionId, String ipAddress) {
        this.sessionId = Objects.requireNonNull(sessionId);
        this.ipAddress = Objects.requireNonNull(ipAddress);
    }

    public String getSessionId() {
        return sessionId;
    }

    public HttpRequestVM sessionId(String id) {
        this.setSessionId(id);
        return this;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public HttpRequestVM ipAddress(String ipAddress) {
        this.setIpAddress(ipAddress);
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean matches(HttpRequestVM that) {
        if (this == that) return true;
        if (that == null) return false;
        return sessionId.equals(that.sessionId)
                || hasEqualValue(ipAddress, that.ipAddress);
    }

    private boolean hasEqualValue(String lhs, String rhs) {
        return StringUtils.hasText(lhs) && lhs.equals(rhs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpRequestVM that = (HttpRequestVM) o;
        return sessionId.equals(that.sessionId) || ipAddress.equals(that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress);
    }

    @Override
    public String toString() {
        return "HttpRequestVM{" +
                "sessionId='" + sessionId + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
