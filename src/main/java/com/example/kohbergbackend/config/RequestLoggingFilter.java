package com.example.kohbergbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Log request details before the request is processed
        logRequest(request);

        // Continue with the request
        filterChain.doFilter(request, response);

        // Log response details after the response is generated
        logResponse(response);
    }

    private void logRequest(HttpServletRequest request) throws IOException {
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("Request: [")
                .append(request.getMethod())
                .append("] ")
                .append(request.getRequestURI());

        // Log request headers
        requestDetails.append("\nRequest Headers:");
        if (request.getHeaderNames() != null) {
            request.getHeaderNames().asIterator()
                    .forEachRemaining(headerName ->
                            requestDetails.append("\n")
                                    .append(headerName)
                                    .append(": ")
                                    .append(request.getHeader(headerName)));
        }

        // Log request body if present
        if (request.getReader() != null) {
            requestDetails.append("\nRequest Body: ").append(getRequestBody(request));
        }

        logger.info(requestDetails.toString());
    }

    private void logResponse(HttpServletResponse response) {
        StringBuilder responseDetails = new StringBuilder();
        responseDetails.append("Response Status Code: ")
                .append(response.getStatus());

        // Log response headers
        responseDetails.append("\nResponse Headers:");
        if (response.getHeaderNames() != null) {
            response.getHeaderNames().forEach(headerName ->
                    responseDetails.append("\n")
                            .append(headerName)
                            .append(": ")
                            .append(response.getHeader(headerName)));
        }

        // Log response body if present
        responseDetails.append("\nResponse Body: ").append(response.getContentType());

        logger.info(responseDetails.toString());
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                requestBody.append(buffer, 0, bytesRead);
            }
        }
        return requestBody.toString();
    }
}
