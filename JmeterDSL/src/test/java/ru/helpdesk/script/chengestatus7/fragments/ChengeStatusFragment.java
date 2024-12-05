package ru.helpdesk.script.chengestatus7.fragments;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import ru.helpdesk.script.opentiket6.fragments.OpenTicketBaseFragment;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class ChengeStatusFragment {
    public static DslTransactionController get() {
        return transaction("TC_ChengeStatus",
                OpenTicketBaseFragment.get(),
                httpSampler("<_/tickets/${index}/", "/tickets/${index}/")
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
                httpSampler(">_/tickets/${index}/update/", "/tickets/${index}/update/")
                        .method(HTTPConstants.POST)
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
