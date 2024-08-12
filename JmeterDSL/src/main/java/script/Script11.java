package script;

import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Script11 {
    public static DslTransactionController script11() {
        return transaction("TC_scenari11",
                threadPause("${__Random(1000,5000,)}"),
                Login1.get(),
                ifController("\"1\" == \"${__Random(1,2,)}\"",
                        WaitSink.get(),
                        Pagination4.get()
                ),
                WaitSink.get(),
                CreateTicket3.get(),
                WaitSink.get(),
                DellTestck8.get(),
                WaitSink.get(),
                Logout10.get()
        );
    }
}
