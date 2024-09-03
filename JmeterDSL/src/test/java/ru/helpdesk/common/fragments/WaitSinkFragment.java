package ru.helpdesk.common.fragments;

import us.abstracta.jmeter.javadsl.core.controllers.DslSimpleController;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class WaitSinkFragment {
    public static DslSimpleController get() {
        return simpleController("SC_wait",
                threadPause(Duration.ofSeconds(6))
        );
    }
}
