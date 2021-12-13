package io.dongtai.agent.plugins;

import java.lang.instrument.Instrumentation;

/**
 * @author owefsad
 */
public interface ILoader {

    /**
     * install agent with premain
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    public void premain(String args, Instrumentation inst);

    /**
     * install agent with attach
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    public void agentmain(String args, Instrumentation inst);
}
