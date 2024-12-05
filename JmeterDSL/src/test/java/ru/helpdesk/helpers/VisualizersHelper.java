package ru.helpdesk.helpers;

import us.abstracta.jmeter.javadsl.core.DslTestPlan;

import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.dashboard.DashboardVisualizer.dashboardVisualizer;

public class VisualizersHelper {
    public static DslTestPlan.TestPlanChild resultTree(boolean enable) {
        if (enable) {
            return resultsTreeVisualizer();
        } else {
            return constantTimer(Duration.ZERO);
        }
    }

    public static DslTestPlan.TestPlanChild resultTree() {
        return resultsTreeVisualizer();
    }

    public static DslTestPlan.TestPlanChild resultDashboard(boolean enable) {
        if (enable) {
            return dashboardVisualizer();
        } else {
            return constantTimer(Duration.ZERO);
        }
    }

    public static DslTestPlan.TestPlanChild resultDashboard() {
        return dashboardVisualizer();
    }

    public static DslTestPlan.TestPlanChild debugPostPro(boolean enable) {
        if (enable) {
            return debugPostProcessor();
        } else {
            return constantTimer(Duration.ZERO);
        }
    }

    public static DslTestPlan.TestPlanChild debugPostPro() {
        return debugPostProcessor();
    }
}
