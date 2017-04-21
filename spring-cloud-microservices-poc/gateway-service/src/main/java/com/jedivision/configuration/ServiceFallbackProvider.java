package com.jedivision.configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.Builder;
import lombok.Data;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

@Data
@Builder
public class ServiceFallbackProvider implements ZuulFallbackProvider {

    private String responseBody;
    private HttpHeaders headers;
    private String route;
    private int rawStatusCode;
    private HttpStatus statusCode;
    private String statusText;

    @Override
    public String getRoute() {
        return this.route;
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse(){

            @Override
            public InputStream getBody() throws IOException {
                if (responseBody != null) {
                    return new ByteArrayInputStream(responseBody.getBytes());
                } else {
                    responseBody = "Service is unavailable.";
                    return new ByteArrayInputStream(responseBody.getBytes());
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                if (headers != null) {
                    return headers;
                } else {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    return headers;
                }
            }

            @Override
            public void close() {
            }

            @Override
            public int getRawStatusCode() throws IOException {
                if (rawStatusCode != 0) {
                    return rawStatusCode;
                } else {
                    rawStatusCode = 500;
                    return rawStatusCode;
                }
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                if (statusCode != null) {
                    return statusCode;
                } else {
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE;
                    return statusCode;
                }
            }

            @Override
            public String getStatusText() throws IOException {
                if (statusText != null) {
                    return statusText;
                } else {
                    statusText = "Service Unavailable";
                    return statusText;
                }
            }
        };
    }
}
