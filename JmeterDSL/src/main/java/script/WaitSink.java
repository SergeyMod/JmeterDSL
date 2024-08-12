package script;

import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class WaitSink {
    public static DslTransactionController get() {
        return transaction("SC_wait",
                threadPause(Duration.ofSeconds(6))
        );
    }
}
