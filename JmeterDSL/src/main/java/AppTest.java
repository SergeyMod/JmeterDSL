import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import script.*;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class AppTest {

    @Test
    public void test() throws IOException {
        TestPlanStats stats = testPlan()
                .tearDownOnlyAfterMainThreadsDone()
                .children(
                        //testElement(new SummaryReport()),
                        vars()
                                .set("host", "192.168.0.100")
                                .set("port", "23232")
                                .set("protocol", "http")
                                .set("username", "admin")
                                .set("password", "admindev")
                                .set("username2", "${__RandomString(10, 1234567890abcdefghiklmnopqrstuvwxyz-,)}")
                                .set("password2", "${__RandomString(8,1234567890abcdefghiklmnopqrstuvwxyz-,)}"),
                        httpCookies(),
                        httpCache(),
                        threadGroup(1, 1,
                                Script11.script11()
                        ),
                        resultsTreeVisualizer()
                ).run();
        assertThat(stats.overall().errorsCount()).isEqualTo(0);
    }

    public static void main(String[] args) {
        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
        LauncherFactory.create()
                .execute(LauncherDiscoveryRequestBuilder.request()
                                .selectors(DiscoverySelectors.selectClass(AppTest.class))
                                .build(),
                        summaryListener);
        TestExecutionSummary summary = summaryListener.getSummary();
        summary.printFailuresTo(new PrintWriter(System.err));
        System.exit(summary.getTotalFailureCount() > 0 ? 1 : 0);
    }

}