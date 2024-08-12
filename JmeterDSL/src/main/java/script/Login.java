package script;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.nio.charset.StandardCharsets;

import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.transaction;

public class Login {

    public static DslTransactionController get() {
        return transaction("TC_login",
                        httpSampler("<_/login/", "${protocol}://${host}:${port}/login/")
                                .header("Referer", "${protocol}://${host}:${port}/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .param("next", "/"),
                        httpSampler(">_/login/", "${protocol}://${host}:${port}/login/")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                .header("Referer", "${protocol}://${host}:${port}/login/?next=/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Origin", "${protocol}://${host}:${port}")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .rawParam("username", "${username2}")
                                .rawParam("password", "${password2}")
                                .param("next", "/")
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .encoding(StandardCharsets.UTF_8)

        );
    }
}
