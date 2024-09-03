package ru.helpdesk.script.createticket3.postprocessor;

import us.abstracta.jmeter.javadsl.core.postprocessors.DslJsr223PostProcessor;

import java.util.Objects;

public class CreateCheck implements DslJsr223PostProcessor.PostProcessorScript {
    @Override
    public void runScript(DslJsr223PostProcessor.PostProcessorVars vars) throws Exception {
        String createCheck = vars.vars.get("create_ticket");
        if  (Objects.equals(createCheck, "Create Ticket") || Objects.equals(createCheck, "create_ticket_ERROR")) {
            vars.prev.setSuccessful(false);
            vars.prev.setSampleLabel(">_/tickets/submit/_ERROR");
        }
    }
}
