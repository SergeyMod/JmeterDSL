package ru.helpdesk.helpers;

import us.abstracta.jmeter.javadsl.http.HttpHeaders;

import static us.abstracta.jmeter.javadsl.JmeterDsl.httpHeaders;

public class HeadersHelper {
    public static HttpHeaders getHeaders() {
        return httpHeaders()
                //.header("Referer", "${protocol}://${host}:${port}/admin/logout/")
                //.header("Accept-Language", "ru,en;q=0.9")
                //.header("Upgrade-Insecure-Requests", "1")
                //.header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36");
                //.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");

    }
}
