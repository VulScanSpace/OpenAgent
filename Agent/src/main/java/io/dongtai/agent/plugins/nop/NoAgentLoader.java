package io.dongtai.agent.plugins.nop;

import io.dongtai.agent.LogUtils;
import io.dongtai.agent.plugins.AbstractLoader;
import java.lang.instrument.Instrumentation;

/**
 * @author owefsad
 */
public class NoAgentLoader extends AbstractLoader {

    public NoAgentLoader(String agentPath) {
        super(agentPath, null, null);
    }

    /**
     * install agent with premain
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    @Override
    public void premain(String args, Instrumentation inst) {
        LogUtils.info("No Agent Need to Load.");
    }

    /**
     * install agent with attach
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    @Override
    public void agentmain(String args, Instrumentation inst) {
        LogUtils.info("No Agent Need to Load.");
    }
}
