package script;

import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;

public class OpenTickets6 {
    public static DslTransactionController get() {
        return transaction("TC_OpenTickets",
                        httpSampler("<_/tickets/${index}/", "${protocol}://${host}:${port}/tickets/${index}/")
                                .header("Referer", "${protocol}://${host}:${port}/tickets/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .children(
                                        regexExtractor("assigned_to", "<td>(.*?) <strong")
                                                .defaultValue("is_assigned_ERROR"),
                                        responseAssertion()
                                                .invertCheck()
                                                .containsSubstrings("<a href=\"/tickets/17/edit/\" class=\"ticket-edit\"><button class=\"btn btn-warning btn-sm\"><i class=\"fas fa-pencil-alt\"></i> Edit</button>")
                                ),
                        ifController("\"${assigned_to}\" == \"Unassigned\"",
                                httpSampler("<_/tickets/${index}/", "${protocol}://${host}:${port}/tickets/${index}/")
                                        .header("Referer", "${protocol}://${host}:${port}/tickets/1/")
                                        .header("Accept-Language", "ru,en;q=0.9")
                                        .header("Upgrade-Insecure-Requests", "1")
                                        .header("Accept-Encoding", "gzip, deflate")
                                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                        .rawParam("take", "")
                        )

        );
    }
}
