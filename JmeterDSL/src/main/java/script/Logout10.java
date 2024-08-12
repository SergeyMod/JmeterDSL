package script;

import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Logout10 {

    public static DslTransactionController get() {
        return transaction("TC_Logout",
                        httpSampler("<_/logout/", "${protocol}://${host}:${port}/logout/")
                                .header("Referer", "${protocol}://${host}:${port}/tickets/?sortx=created&status=5&date_from=&date_to=&q=")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .children(
                                        responseAssertion()
                                                .containsSubstrings("login/?next=/\" id=\"userDropdown\" role=\"button\"")
                                )

        );
    }
}
