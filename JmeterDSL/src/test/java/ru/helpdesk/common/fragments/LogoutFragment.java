package ru.helpdesk.common.fragments;

import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class LogoutFragment {

    public static DslTransactionController get() {
        return transaction("TC_Logout",
                        httpSampler("<_/logout/", "/logout/")
                                .children(
                                        responseAssertion()
                                                .containsSubstrings("login/?next=/\" id=\"userDropdown\" role=\"button\"")
                                )

        );
    }
}
