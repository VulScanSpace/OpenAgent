package io.dongtai.agent.plugins.openrrasp;

import io.dongtai.agent.LogUtils;
import io.dongtai.agent.plugins.AbstractLoader;

/**
 * @author owefsad
 */
public class OpenRaspLoader extends AbstractLoader {

    private static final String PREMAIN_CLASS = "com.baidu.openrasp.Agent";
    private static final String AGENTMAIN_CLASS = "com.baidu.openrasp.Agent";

    public OpenRaspLoader(String agentPath) {
        super(agentPath, PREMAIN_CLASS, AGENTMAIN_CLASS);
        LogUtils.info("Init OpenRASP, package is: " + agentPath);
    }
}
