package ru.helpdesk.script.addusers2.preprocessor;

import us.abstracta.jmeter.javadsl.core.preprocessors.DslJsr223PreProcessor.PreProcessorScript;
import us.abstracta.jmeter.javadsl.core.preprocessors.DslJsr223PreProcessor.PreProcessorVars;

import java.util.Random;

import static ru.helpdesk.script.addusers2.helpers.GenerateHelpers.*;

public class GetUserPass implements PreProcessorScript {
    @Override
    public void runScript(PreProcessorVars s) {

        s.vars.put("username", get(10,"abcdefghiklmnopqrstuvwxyz"));
        s.vars.put("password", get(10));
    }
}
