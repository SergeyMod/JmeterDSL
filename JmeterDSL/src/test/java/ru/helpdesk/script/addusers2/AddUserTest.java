package ru.helpdesk.script.addusers2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.helpdesk.script.addusers2.fragments.AddUsersFragment;
import ru.helpdesk.script.addusers2.samplers.AddUserTreadGroup;
import ru.helpdesk.script.admin_login1.fragments.AdminLoginFragment;
import ru.helpdesk.helpers.PropertiesHelper;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.core.engines.EmbeddedJmeterEngine;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import static ru.helpdesk.helpers.HttpHelper.getHttpDefaults;
import static ru.helpdesk.helpers.CacheHelper.getCacheManager;
import static ru.helpdesk.helpers.CookiesHelper.getCookiesClean;
import static ru.helpdesk.helpers.HeadersHelper.getHeaders;
import static ru.helpdesk.helpers.LogHelper.getTestResultString;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class AddUserTest {

    boolean debugEnable;
    boolean errorLogEnable;
    boolean influxDBLogEnable;
    boolean resultTreeEnable;
    boolean resultDashboardEnable;
    boolean debugPostProcessorEnable;
    double throughputPerMinute;

    static final Logger logger = LogManager.getLogger(AddUserTest.class);

    EmbeddedJmeterEngine embeddedJmeterEngine = new EmbeddedJmeterEngine();

    Properties properties = new Properties();

    @BeforeTest
    private void init() throws IOException, InterruptedException, TimeoutException {
        properties = PropertiesHelper.readAdminLoginProperties();
        PropertiesHelper.setPropertiesToEngine(embeddedJmeterEngine, properties);

        debugEnable = Boolean.parseBoolean(properties.getProperty("DEBUG_ENABLE"));
        errorLogEnable = Boolean.parseBoolean(properties.getProperty("ERROR_LOG_ENABLE"));
        influxDBLogEnable = Boolean.parseBoolean(properties.getProperty("INFLUXDB_LOG_ENABLE"));
        resultTreeEnable = Boolean.parseBoolean(properties.getProperty("RESULT_TREE_ENABLE"));
        resultDashboardEnable = Boolean.parseBoolean(properties.getProperty("RESULT_DASHBOARD_ENABLE"));
        debugPostProcessorEnable = Boolean.parseBoolean(properties.getProperty("DEBUG_POSTPROCESSOR_ENABLE"));
        throughputPerMinute = Double.parseDouble(properties.getProperty("THROUGHPUT"));

//        testPlan(
//                getHttpDefaults(),
//                getHeaders(),
//                getCacheManager(),
//                getCookiesClean(),
//                setupThreadGroup("Login",
//                        LoginFragment.get(),
//                        jsr223PreProcessor(
//                                s-> embeddedJmeterEngine
//                                        .prop("csrfmiddlewaretoken", s.vars.get("csrfmiddlewaretoken"))
////                                s.vars.put("csrfmiddlewaretoken", s.props.get("csrfmiddlewaretoken").toString());
////                                s.props.put("__ada", "dasda"); })
//                        )
//
//                )
//        ).runIn(embeddedJmeterEngine);


    }

    @Test
    public void test() throws IOException, InterruptedException, TimeoutException {
        TestPlanStats stats = testPlan(
                getHttpDefaults(),
                getHeaders(),
                getCookiesClean(),
                getCacheManager(),
                AddUserTreadGroup.getThreadGroup("TG_Add_user", debugEnable)
                        .children(
                                AdminLoginFragment.get(),
                                AddUsersFragment.get()
                        ),
                resultsTreeVisualizer(),
                debugPostProcessor()
        ).runIn(embeddedJmeterEngine);
        logger.info(getTestResultString(stats));
    }

}
