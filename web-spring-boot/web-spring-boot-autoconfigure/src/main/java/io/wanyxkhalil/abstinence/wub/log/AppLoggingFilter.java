package io.wanyxkhalil.abstinence.wub.log;

import com.alibaba.fastjson.JSONObject;
import io.wanyxkhalil.abstinence.wub.util.ClockUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Clock;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@NoArgsConstructor
public class AppLoggingFilter extends OncePerRequestFilter {

    /**
     * 是否打印返回值
     */
    private boolean responseLogging;

    public AppLoggingFilter(boolean responseLogging) {
        this.responseLogging = responseLogging;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startTime = ClockUtils.mills();

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(requestWrapper, responseWrapper);

        Map<String, Object> trace = new LinkedHashMap<>();
        Map<String, Object> headers = new LinkedHashMap<>();
        Map<String, Object> bodys = new LinkedHashMap<>();
        trace.put("method", requestWrapper.getMethod());
        trace.put("path", requestWrapper.getRequestURI());
        trace.put("remoteAddress", requestWrapper.getRemoteAddr());

        trace.put("headers", headers);
        headers.put("request", getRequestHeaders(requestWrapper));
        headers.put("response", getResponseHeaders(responseWrapper));

        trace.put("body", bodys);
        bodys.put("request", getRequestBody(requestWrapper));
        if (responseLogging) {
            bodys.put("response", getResponseBody(responseWrapper));
        }

        long timeTaken = ClockUtils.mills() - startTime;
        trace.put("timeTaken", Objects.toString(timeTaken));

        log.info(JSONObject.toJSONString(trace));

        responseWrapper.copyBodyToResponse();
    }

    private String getRequestBody(ContentCachingRequestWrapper requestWrapper) {
        try {
            String str = IOUtils.toString(requestWrapper.getContentAsByteArray(), UTF_8.name());
            return StringUtils.deleteWhitespace(str);
        } catch (IOException e) {
            log.error("", e);
            return null;
        }
    }

    private String getResponseBody(ContentCachingResponseWrapper responseWrapper) {
        try {
            return IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Object> getRequestHeaders(HttpServletRequest request) {
        Map<String, Object> headers = new LinkedHashMap<>();

        Set<String> excludedHeaders = new HashSet<>();
        excludedHeaders.add("authorization");

        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (!excludedHeaders.contains(name.toLowerCase())) {
                headers.put(name, getHeaderValue(request, name));
            }
        }
        return headers;
    }

    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
        Map<String, String> headers = new LinkedHashMap<>();
        for (String header : response.getHeaderNames()) {
            String value = response.getHeader(header);
            headers.put(header, value);
        }
        headers.put("status", String.valueOf(response.getStatus()));
        return headers;
    }

    private Object getHeaderValue(HttpServletRequest request, String name) {
        List<String> value = Collections.list(request.getHeaders(name));
        if (value.size() == 1) {
            return value.get(0);
        }
        if (value.isEmpty()) {
            return "";
        }
        return value;
    }
}