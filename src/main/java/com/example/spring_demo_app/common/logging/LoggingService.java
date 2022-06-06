package com.example.spring_demo_app.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LoggingService {
    private static final String REQUEST_ID = "request_id";

    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        if (httpServletRequest.getRequestURI().contains("medias")) {
            return;
        }
        Object requestId = httpServletRequest.getAttribute(REQUEST_ID);

        String data = "\n======||==============> LOGGING REQUEST\n\n" +
                "[REQUEST-ID]:" + requestId + "\n" +
                GsonParserUtils.parseObjectToString(body) +
                "\n\n<===============||===== LOGGING REQUEST\n";
        log.info(data);
    }

    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        if (httpServletRequest.getRequestURI().contains("medias")) return;

        Object requestId = httpServletRequest.getAttribute(REQUEST_ID);

        String data = "\n======||==============> LOGGING RESPONSE\n" +
                "[REQUEST-ID]:" + requestId + "\n\n" +
                GsonParserUtils.parseObjectToString(body) +
                "\n\n<===============||===== LOGGING RESPONSE";

        log.info(data);
    }
}