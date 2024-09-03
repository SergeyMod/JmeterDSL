//import org.junit.platform.engine.discovery.DiscoverySelectors;
//import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
//import org.junit.platform.launcher.core.LauncherFactory;
//import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
//import org.junit.platform.launcher.listeners.TestExecutionSummary;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.helpdesk.common.samplers.TreadGroup;
import ru.helpdesk.helpers.PropertiesHelper;
import ru.helpdesk.script.Script11;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.core.engines.EmbeddedJmeterEngine;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import static ru.helpdesk.helpers.CacheHelper.getCacheManager;
import static ru.helpdesk.helpers.CookiesHelper.getCookiesClean;
import static ru.helpdesk.helpers.HeadersHelper.getHeaders;
import static ru.helpdesk.helpers.HttpHelper.getHttpDefaults;
import static ru.helpdesk.helpers.LogHelper.getTestResultString;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class AppTest {
//    public static void main(String[] args) {
//        AdminLoginTest;
//    }

    boolean debugEnable;
    boolean errorLogEnable;
    boolean influxDBLogEnable;
    boolean resultTreeEnable;
    boolean resultDashboardEnable;
    boolean debugPostProcessorEnable;
    double throughputPerMinute;

    static final Logger logger = LogManager.getLogger(AppTest.class);

    EmbeddedJmeterEngine embeddedJmeterEngine = new EmbeddedJmeterEngine();

    Properties properties = new Properties();

    @BeforeTest
    private void init() throws IOException {
        properties = PropertiesHelper.readAdminLoginProperties();
        PropertiesHelper.setPropertiesToEngine(embeddedJmeterEngine, properties);

        debugEnable = Boolean.parseBoolean(properties.getProperty("DEBUG_ENABLE"));
        errorLogEnable = Boolean.parseBoolean(properties.getProperty("ERROR_LOG_ENABLE"));
        influxDBLogEnable = Boolean.parseBoolean(properties.getProperty("INFLUXDB_LOG_ENABLE"));
        resultTreeEnable = Boolean.parseBoolean(properties.getProperty("RESULT_TREE_ENABLE"));
        resultDashboardEnable = Boolean.parseBoolean(properties.getProperty("RESULT_DASHBOARD_ENABLE"));
        debugPostProcessorEnable = Boolean.parseBoolean(properties.getProperty("DEBUG_POSTPROCESSOR_ENABLE"));
        throughputPerMinute = Double.parseDouble(properties.getProperty("THROUGHPUT"));


    }

    @Test
    public void test() throws IOException, InterruptedException, TimeoutException {

        TestPlanStats stats = testPlan(
                getHttpDefaults(),
                getHeaders(),
                getCookiesClean(),
                getCacheManager(),
                TreadGroup.getThreadGroup("TG_Pagination", debugEnable)
                        .children(
                                Script11.run()
                        ),
                resultsTreeVisualizer(),
                debugPostProcessor()
        ).runIn(embeddedJmeterEngine);
        logger.info(getTestResultString(stats));
    }

//    public static void main(String[] args) {
//        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
//        LauncherFactory.create()
//                .execute(LauncherDiscoveryRequestBuilder.request()
//                                .selectors(DiscoverySelectors.selectClass(AppTest.class))
//                                .build(),
//                        summaryListener);
//        TestExecutionSummary summary = summaryListener.getSummary();
//        summary.printFailuresTo(new PrintWriter(System.err));
//        System.exit(summary.getTotalFailureCount() > 0 ? 1 : 0);
//    }
//
}