package com.bayou.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * File: Server
 * Package: com.bayou.utils
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class Server {
    private static final String url = "http://localhost:8090/service/v1";

    public static String url() {
        return url;
    }

    public static HttpHeaders createHeadersAuthJson() {
        String auth = System.getenv("AUTH_USERNAME") + ":" + System.getenv("AUTH_PASSWORD");
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public static HttpHeaders createHeadersJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
