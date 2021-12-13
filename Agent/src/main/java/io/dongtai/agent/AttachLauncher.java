package io.dongtai.agent;

import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.VirtualMachine;
import java.util.Properties;

/**
 * @author owefsad
 */
public class AttachLauncher {

    private static final String AGENT_PATH = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();

    public static void attach(String pid, String enginePath) throws Exception {
        VirtualMachine vmObj = null;
        try {
            LogUtils.info("trying attach to process " + pid + ", agent address is " + AGENT_PATH);
            vmObj = VirtualMachine.attach(pid);
            Properties targetSystemProperties = vmObj.getSystemProperties();
            String targetJavaVersion = JavaVersionUtils.javaVersionStr(targetSystemProperties);
            String currentJavaVersion = JavaVersionUtils.javaVersionStr();
            if (targetJavaVersion != null && currentJavaVersion != null) {
                if (!targetJavaVersion.equals(currentJavaVersion)) {
                    LogUtils.info(
                            "Current VM java version: " + currentJavaVersion + " do not match target VM java version: "
                                    + targetJavaVersion + ", attach may fail.");
                    LogUtils.info(
                            "Target VM JAVA_HOME is " + targetSystemProperties.getProperty("java.home")
                                    + ", DongTai-Agent JAVA_HOME is " + System.getProperty("java.home")
                                    + ", try to set the same JAVA_HOME.");
                }
            }
            try {
                vmObj.loadAgent(AGENT_PATH, enginePath);
                LogUtils.info("attach to process " + pid + " success.");
            } catch (AgentLoadException e) {
                if (targetJavaVersion != null && currentJavaVersion != null) {
                    LogUtils.info("attach to process " + pid + " success.");
                }
            }

        } finally {
            if (null != vmObj) {
                vmObj.detach();
            }
        }
    }
}
