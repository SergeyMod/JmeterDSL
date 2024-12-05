package ru.helpdesk.script.filter5;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.helpdesk.common.fragments.LoginFragment;
import ru.helpdesk.common.samplers.TreadGroup;
import ru.helpdesk.helpers.PropertiesHelper;
import ru.helpdesk.script.addusers2.fragments.AddUsersFragment;
import ru.helpdesk.script.admin_login1.fragments.AdminLoginFragment;
import ru.helpdesk.script.filter5.fragments.FilterFragment;
import ru.helpdesk.common.fragments.LogoutFragment;
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

public class FilterTest {
    boolean debugEnable;
    boolean errorLogEnable;
    boolean influxDBLogEnable;
    boolean resultTreeEnable;
    boolean resultDashboardEnable;
    boolean debugPostProcessorEnable;
    double throughputPerMinute;

    static final Logger logger = LogManager.getLogger(FilterTest.class);

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
                TreadGroup.getThreadGroup("TG_Filter", debugEnable)
                        .children(
                                AdminLoginFragment.get(),
                                AddUsersFragment.get(),
                                LogoutFragment.get(),
                                LoginFragment.get(),
                                FilterFragment.get()
                                ),
                resultsTreeVisualizer(),
                debugPostProcessor()
        ).runIn(embeddedJmeterEngine);
        logger.info(getTestResultString(stats));
    }
}
