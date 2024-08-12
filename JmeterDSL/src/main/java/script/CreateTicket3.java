package script;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.nio.charset.StandardCharsets;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class CreateTicket3 {
    public static DslTransactionController get() {
        return transaction("TC_createTicket3",
                        httpSampler("<_/tickets/submit/", "${protocol}://${host}:${port}/tickets/submit/")
                                .header("Referer", "${protocol}://${host}:${port}/tickets/1/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR"),
                                        regexExtractor("priority", "<option value=\\\"(\\d)\\\">(\\d)\\.")
                                                .matchNumber(0)
                                                .defaultValue("priority_ERROR"),
                                        regexExtractor("assigned_to", "<option value=\\\"(.+?)\\\">${username2}")
                                                .matchNumber(0)
                                                .defaultValue("assigned_to_ERROR")
                                ),
                        httpSampler(">_/tickets/submit/", "${protocol}://${host}:${port}/tickets/submit/")
                                .method(HTTPConstants.POST)
                                .header("Referer", "${protocol}://${host}:${port}/tickets/submit/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Origin", "${protocol}://${host}:${port}")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Cache-Control", "max-age=0")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .bodyPart("csrfmiddlewaretoken", "${csrfmiddlewaretoken}", ContentType.TEXT_PLAIN)
                                .bodyPart("queue", "${__Random(1,2,)}", ContentType.TEXT_PLAIN)
                                .bodyPart("title", "${title}", ContentType.TEXT_PLAIN)
                                .bodyPart("body", "${__RandomString(16, 1234567890abcdefghiklmnopqrstuvwxyz-,)}", ContentType.TEXT_PLAIN)
                                .bodyPart("priority", "${priority}", ContentType.TEXT_PLAIN)
                                .bodyPart("due_date", "${__time(yyyy-MM-dd,)} 00:00:00", ContentType.TEXT_PLAIN)
                                .bodyPart("attachment", "", ContentType.APPLICATION_OCTET_STREAM)
                                .bodyPart("submitter_email", "${username2}@example.org", ContentType.TEXT_PLAIN)
                                .bodyPart("assigned_to", "${assigned_to}", ContentType.TEXT_PLAIN)
                                .encoding(StandardCharsets.UTF_8)
                                .children(
                                        jsr223PreProcessor("\n"
                                                + "vars.put(\"title\", \"${__RandomString(10, 1234567890abcdefghiklmnopqrstuvwxyz-,)}\");"),
                                        responseAssertion()
                                                .containsSubstrings("${title}")
                                )

        );
    }
}
