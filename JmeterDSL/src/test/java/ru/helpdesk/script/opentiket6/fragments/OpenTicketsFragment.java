package ru.helpdesk.script.opentiket6.fragments;

import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;
import us.abstracta.jmeter.javadsl.core.postprocessors.DslJsonExtractor;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;

public class OpenTicketsFragment {
    public static DslTransactionController get() {
        return transaction("TC_OpenTickets",
                OpenTicketBaseFragment.get(),
                httpSampler("<_/tickets/${index}/", "/tickets/${index}/")
                                .children(
                                        regexExtractor("assigned_to", "<td>(.*?) <strong")
                                                .defaultValue("is_assigned_ERROR"),
                                        responseAssertion()
                                                .invertCheck()
                                                .containsSubstrings("<a href=\"/tickets/17/edit/\" class=\"ticket-edit\"><button class=\"btn btn-warning btn-sm\"><i class=\"fas fa-pencil-alt\"></i> Edit</button>")
                                ),
                        ifController("\"${assigned_to}\" == \"Unassigned\"",
                                httpSampler("<_/tickets/${index}/", "/tickets/${index}/")
                                        .rawParam("take", "")
                        )

        );
    }
}
