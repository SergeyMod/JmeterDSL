package ru.helpdesk.script.addusers2.fragments;

import org.apache.jmeter.protocol.http.util.HTTPConstants;
import ru.helpdesk.script.addusers2.preprocessor.GetUserPass;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.nio.charset.StandardCharsets;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class AddUsersFragment {
    public static DslTransactionController get() {
        return transaction("TC_AddUsers2",
                        httpSampler("<_/admin/auth/user/add/", "/admin/auth/user/add/")
                                .children(
                                        regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                .matchNumber(0)
                                                .defaultValue("csrfmiddlewaretoken_ERROR")
                                ),
                        httpSampler(">_/admin/auth/user/add/", "/admin/auth/user/add/")
                                .method(HTTPConstants.POST)
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")

                                .rawParam("username", "${username}")
                                .rawParam("password1", "${password}")
                                .rawParam("password2", "${password}")
                                .rawParam("_save", "Save")
                                .encoding(StandardCharsets.UTF_8)
                                .children(
                                        jsr223PreProcessor(GetUserPass.class),
                                        regexExtractor("userNumber", "/admin/auth/user/(.*?)/change/")
                                                .matchNumber(0)
                                                .defaultValue("userNumber_ERROR")
                                ),
                        httpSampler(">_/admin/auth/user/${userNumber}/change/", "/admin/auth/user/${userNumber}/change/")
                                .method(HTTPConstants.POST)
                                .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                .rawParam("username", "${username}")
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
                                .rawParam("_save", "Save")
                                .children(
                                        responseAssertion()
                                                .containsSubstrings("${username}")
                                )

        );
    }
}
