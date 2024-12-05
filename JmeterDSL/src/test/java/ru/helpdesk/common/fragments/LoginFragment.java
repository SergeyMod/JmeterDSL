package ru.helpdesk.common.fragments;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import us.abstracta.jmeter.javadsl.core.controllers.DslSimpleController;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.nio.charset.StandardCharsets;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class LoginFragment {

    public static DslSimpleController get() {
        return simpleController("SC_login",
                        httpSampler("<_/login/", "/login/")
                                .param("next", "/")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                            .matchNumber(0)
                                            .defaultValue("csrfmiddlewaretoken_ERROR")
                                ),
                        httpSampler(">_/login/", "/login/")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                .rawParam("username", "${username}")
                                .rawParam("password", "${password}")
                                .param("next", "/")
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .encoding(StandardCharsets.UTF_8)

        );
    }
}
