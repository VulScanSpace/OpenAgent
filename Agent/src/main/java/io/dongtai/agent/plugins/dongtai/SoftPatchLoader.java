package io.dongtai.agent.plugins.dongtai;

import io.dongtai.agent.LogUtils;
import io.dongtai.agent.plugins.AbstractLoader;

/**
 * @author owefsad
 */
public class SoftPatchLoader extends AbstractLoader {

    private static final String PREMAIN_CLASS = "io.dongtai.rasp.Main";
    private static final String AGENTMAIN_CLASS = "io.dongtai.rasp.Main";

    public SoftPatchLoader(String agentPath) {
        super(agentPath, PREMAIN_CLASS, AGENTMAIN_CLASS);
        LogUtils.info("Init DongTai SoftPatch, package is: " + agentPath);
    }
}
