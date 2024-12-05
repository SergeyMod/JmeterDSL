package ru.helpdesk.script;

import ru.helpdesk.common.fragments.WaitSinkFragment;
import ru.helpdesk.script.admin_login1.fragments.AdminLoginFragment;
import ru.helpdesk.script.createticket3.fragments.CreateTicketFragment;
import ru.helpdesk.script.dellticket8.fragments.DellTicketFragment;
import ru.helpdesk.common.fragments.LogoutFragment;
import ru.helpdesk.script.pagination4.fragments.PaginationFragment;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;

import java.util.Random;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Script11 {
    public static DslTransactionController run() {
        return transaction("TC_scenari11",
                threadPause("${__Random(1000,5000,)}"),
                AdminLoginFragment.get()
                        .children(
                            jsr223PostProcessor(s->s.vars.put("username", s.props.get("ADMIN_LOGIN").toString()
                        ))),
                ifController(s-> 1 == new Random().nextInt(2),
                        WaitSinkFragment.get(),
                        PaginationFragment.get()
                ),
                WaitSinkFragment.get(),
                CreateTicketFragment.get(),
                WaitSinkFragment.get(),
                DellTicketFragment.get(),
                WaitSinkFragment.get(),
                LogoutFragment.get()
        );
    }
}
