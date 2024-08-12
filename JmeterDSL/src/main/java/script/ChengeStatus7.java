package script;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class ChengeStatus7 {
    public static DslTransactionController get() {
        return transaction("TC_ChengeStatus",
                        httpSampler("<_/tickets/${index}/", "${protocol}://${host}:${port}/tickets/${index}/")
                                .header("Referer", "${protocol}://${host}:${port}/tickets/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR"),
                                        regexExtractor("checked", "'new_status' value='(.+)' checked='checked'")
                                                .defaultValue("checked_ERROR"),
                                        regexExtractor("new_status", "'new_status' value='(\\d)' (?!.+'checked')")
                                                .matchNumber(0)
                                                .defaultValue("new_status_ERROR"),
                                        regexExtractor("priority", "value='(\\d)' selected='selected'")
                                                .defaultValue("priority_ERROR"),
                                        regexExtractor("public", "public' value='(\\d)' checked")
                                                .matchNumber(0)
                                                .defaultValue("public_ERROR"),
                                        regexExtractor("status", "'new_status' value='(\\d)' (.+'checked')")
                                                .matchNumber(0)
                                                .template("$2$")
                                                .defaultValue("status_ERROR"),
                                        responseAssertion()
                                                .invertCheck()
                                                .containsSubstrings("<a href=\"/tickets/17/edit/\" class=\"ticket-edit\"><button class=\"btn btn-warning btn-sm\"><i class=\"fas fa-pencil-alt\"></i> Edit</button>")
                                ),
                        httpSampler(">_/tickets/${index}/update/", "${protocol}://${host}:${port}/tickets/${index}/update/")
                                .method(HTTPConstants.POST)
                                .header("Referer", "${protocol}://${host}:${port}/tickets/4/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Origin", "${protocol}://${host}:${port}")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .bodyPart("comment", "${__RandomString(10, 1234567890abcdefghiklmnopqrstuvwxyz-,)}", ContentType.TEXT_PLAIN)
                                .bodyPart("new_status", "${new_status}", ContentType.TEXT_PLAIN)
                                .bodyPart("public", "${public}", ContentType.TEXT_PLAIN)
                                .bodyPart("time_spent", "", ContentType.TEXT_PLAIN)
                                .bodyPart("title", "", ContentType.TEXT_PLAIN)
                                .bodyPart("owner", "0", ContentType.TEXT_PLAIN)
                                .bodyPart("priority", "${priority}", ContentType.TEXT_PLAIN)
                                .bodyPart("due_date", "", ContentType.TEXT_PLAIN)
                                .bodyPart("attachment", "", ContentType.APPLICATION_OCTET_STREAM)
                                .bodyPart("csrfmiddlewaretoken", "${csrfmiddlewaretoken}", ContentType.TEXT_PLAIN)
                                .children(
                                        responseAssertion()
                                                .invertCheck()
                                                .containsSubstrings("${status}")
                                )

        );
    }
}
