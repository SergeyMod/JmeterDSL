package script;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.nio.charset.StandardCharsets;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class LoginAndAdd2a {
    private DslTransactionController get() {
        return transaction("SC_LoginAndAdd",
                        httpSampler("<_/admin/login/", "${protocol}://${host}:${port}/admin/login/")
                                .header("Referer", "${protocol}://${host}:${port}/admin/logout/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .param("next", "/admin/")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR")
                                ),
                        httpSampler(">_/admin/login/", "${protocol}://${host}:${port}/admin/login/")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                .header("Referer", "${protocol}://${host}:${port}/admin/login/?next=/admin/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Origin", "${protocol}://${host}:${port}")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .rawParam("username", "${username}")
                                .rawParam("password", "${password}")
                                .param("next", "/admin/")
                                .encoding(StandardCharsets.UTF_8),
                        httpSampler("<_/admin/auth/user/add/", "${protocol}://${host}:${port}/admin/auth/user/add/")
                                .header("Referer", "${protocol}://${host}:${port}/admin/auth/user/add/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR")
                                ),
                        httpSampler(">_/admin/auth/user/add/", "${protocol}://${host}:${port}/admin/auth/user/add/")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                .header("Referer", "${protocol}://${host}:${port}/admin/auth/user/add/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Origin", "${protocol}://${host}:${port}")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .rawParam("username", "${username2}")
                                .rawParam("password1", "${password2}")
                                .rawParam("password2", "${password2}")
                                .rawParam("_save", "Save")
                                .encoding(StandardCharsets.UTF_8)
                                .children(
                                        regexExtractor("userNumber", "/admin/auth/user/(.*?)/change/")
                                                .matchNumber(0)
                                                .defaultValue("userNumber_ERROR")
                                ),
                        httpSampler(">_/admin/auth/user/${userNumber}/change/", "${protocol}://${host}:${port}/admin/auth/user/${userNumber}/change/")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                .header("Referer", "${protocol}://${host}:${port}/admin/auth/user/6/change/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Origin", "${protocol}://${host}:${port}")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .rawParam("username", "${username2}")
                                .rawParam("first_name", "")
                                .rawParam("last_name", "")
                                .rawParam("email", "")
                                .rawParam("is_active", "on")
                                .rawParam("is_staff", "on")
                                .rawParam("last_login_0", "")
                                .rawParam("last_login_1", "")
                                .rawParam("date_joined_0", "${__time(yyyy-MM-dd,)}")
                                .param("date_joined_1", "${__time(hh:mm:ss,)}")
                                .rawParam("initial-date_joined_0", "")
                                .param("initial-date_joined_1", "")
                                .rawParam("_save", "Save"),
                        httpSampler("<_/admin/auth/user/", "${protocol}://${host}:${port}/admin/auth/user/")
                                .header("Referer", "${protocol}://${host}:${port}/admin/auth/user/6/change/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .children(
                                        responseAssertion()
                                                .containsSubstrings("${username2}")
                                )

        );
    }
}
