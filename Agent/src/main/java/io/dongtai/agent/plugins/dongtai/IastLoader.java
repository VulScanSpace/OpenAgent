package io.dongtai.agent.plugins.dongtai;

import io.dongtai.agent.LogUtils;
import io.dongtai.agent.plugins.AbstractLoader;

/**
 * @author owefsad
 */
public class IastLoader extends AbstractLoader {

    private static final String PREMAIN_CLASS = "com.secnium.iast.agent.AgentLauncher";
    private static final String AGENTMAIN_CLASS = "com.secnium.iast.agent.AgentLauncher";

    public IastLoader(String agentPath) {
        super(agentPath, PREMAIN_CLASS, AGENTMAIN_CLASS);
        LogUtils.info("Init DongTai IAST, package is: " + agentPath);
    }
}
