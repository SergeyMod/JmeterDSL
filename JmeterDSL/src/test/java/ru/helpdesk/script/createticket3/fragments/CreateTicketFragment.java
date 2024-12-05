package ru.helpdesk.script.createticket3.fragments;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import ru.helpdesk.script.createticket3.postprocessor.CreateCheck;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.nio.charset.StandardCharsets;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class CreateTicketFragment {
    public static DslTransactionController get() {
        return transaction("TC_createTicket3",
                        httpSampler("<_/tickets/submit/", "/tickets/submit/")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR"),
                                        regexExtractor("priority", "<option value=\\\"(\\d)\\\">(\\d)\\.")
                                                .matchNumber(0)
                                                .defaultValue("priority_ERROR"),
                                        regexExtractor("assigned_to", "<option value=\\\"(.+?)\\\">${username}")
                                                .matchNumber(0)
                                                .defaultValue("assigned_to_ERROR")
                                ),
                        httpSampler(">_/tickets/submit/", "/tickets/submit/")
                                .method(HTTPConstants.POST)
                                .bodyPart("csrfmiddlewaretoken", "${csrfmiddlewaretoken}", ContentType.TEXT_PLAIN)
                                .bodyPart("queue", "${__Random(1,2,)}", ContentType.TEXT_PLAIN)
                                .bodyPart("title", "${title}", ContentType.TEXT_PLAIN)
                                .bodyPart("body", "${__RandomString(16, 1234567890abcdefghiklmnopqrstuvwxyz-,)}", ContentType.TEXT_PLAIN)
                                .bodyPart("priority", "${priority}", ContentType.TEXT_PLAIN)
                                .bodyPart("due_date", "${__time(yyyy-MM-dd,)} 00:00:00", ContentType.TEXT_PLAIN)
                                .bodyPart("attachment", "", ContentType.APPLICATION_OCTET_STREAM)
                                .bodyPart("submitter_email", "${username}@example.org", ContentType.TEXT_PLAIN)
                                .bodyPart("assigned_to", "${assigned_to}", ContentType.TEXT_PLAIN)
                                .encoding(StandardCharsets.UTF_8)
                                .children(
                                        regexExtractor("create_ticket", "active\\\">[\\n\\s]*(.+)[\\n\\s]*</li")
                                                .matchNumber(0)
                                                .defaultValue("create_ticket_ERROR"),
                                        jsr223PreProcessor(
                                                "vars.put(\"title\", \"${__RandomString(15, 1234567890abcdefghiklmnopqrstuvwxyz-,)}\");"),
                                        jsr223PostProcessor(CreateCheck.class)
                                )

        );
    }
}
