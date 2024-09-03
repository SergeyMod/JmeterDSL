package ru.helpdesk.script.admin_login1.fragments;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import ru.helpdesk.script.admin_login1.postprocessors.LoginCheck;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class AdminLoginFragment {

    public static DslTransactionController get() {
        return transaction("TC_AdminLogin",
                        httpSampler("<_/admin/login/", "/admin/login/")
                                .method(HTTPConstants.GET)
                                .param("next", "/admin/")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR")
                                ),
                     httpSampler(">_/admin/login/", "/admin/login/")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .rawParam("username", "${__P(ADMIN_LOGIN)}")
                                .rawParam("password", "${__P(ADMIN_PASS)}")
                                .rawParam("next", "/admin/")
                                .children(
                                        regexExtractor("login_check", " (\\w+),\\n *<st")
                                                .defaultValue("login_check_error"),
                                        jsr223PostProcessor(LoginCheck.class)
                                )
        );
    }
}
