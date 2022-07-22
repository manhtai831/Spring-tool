package com.example.spring_demo_app.common.logging;


import com.example.spring_demo_app.common.utils.RandomString;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
public class CustomURLFilter implements Filter {

    public static final String REQUEST_ID = "request_id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String requestId =new RandomString(16).nextString();
        servletRequest.setAttribute(REQUEST_ID, requestId);
        logRequest((HttpServletRequest) servletRequest, requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


    private void logRequest(HttpServletRequest request, String requestId) {
        if (request != null) {
            StringBuilder data = new StringBuilder();
            data.append("\nLOGGING REQUEST-----------------------------------\n")
                    .append("[REQUEST-ID]: ").append(requestId).append("\n")
                    .append("[PATH]: ").append(request.getRequestURI()).append("\n")
                    .append("[QUERIES]: ").append(request.getQueryString()).append("\n")
                    .append("[HEADERS]: ").append("\n");

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                data.append("---").append(key).append(" : ").append(value).append("\n");
            }
            data.append("-----------------------------------LOGGING REQUEST\n");

            log.info(data.toString());
        }
    }
}