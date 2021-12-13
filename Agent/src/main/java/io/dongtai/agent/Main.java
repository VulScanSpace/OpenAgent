package io.dongtai.agent;

import io.dongtai.agent.plugins.ILoader;
import io.dongtai.agent.plugins.dongtai.IastLoader;
import io.dongtai.agent.plugins.dongtai.SoftPatchLoader;
import io.dongtai.agent.plugins.openrrasp.OpenRaspLoader;
import java.io.File;
import java.lang.instrument.Instrumentation;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @author owefsad
 */
public class Main {

    public static void main(String[] args) {
        Options attachOptions = new Options();

        attachOptions.addOption(build("p", "pid", "webserver process id"));
        attachOptions.addOption(build("e", "engine", "Java Agent Path, eg: /opt/DongTai/agent.jar"));
        attachOptions.addOption(build("a", "args", "Java Agent Args, eg: -javaagent:/tmp/baidu/rasp.jar=install"));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine result = null;
        try {
            result = parser.parse(attachOptions, args);
            if (result.hasOption("p") && result.hasOption("e")) {
                String pid = result.getOptionValue("p");
                String enginePath = result.getOptionValue("e");
                File agentFile = new File(enginePath);
                String agentArgs = agentFile.getAbsolutePath();
                if (result.hasOption("a")) {
                    agentArgs = agentArgs + "&" + result.getOptionValue("a");
                }

                if (agentFile.exists()) {
                    AttachLauncher.attach(pid, agentArgs);
                } else {
                    LogUtils.error("Java Agent not exist, please use absolute path. eg: /opt/DongTai/agent.jar");
                }
            } else {
                formatter.printHelp("java -jar agent.jar", attachOptions, true);
            }
        } catch (Throwable t) {
            LogUtils.error("Start OpenAgent failed, exception stack trace: ");
            t.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * install agent with premain
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    public static void premain(String args, Instrumentation inst) {
        String[] agentArgs = parseArgs(args);
        String mode = System.getProperty("agent.mode", "iast");
        ILoader loader = null;
        LogUtils.info("OpenAgent mode: " + mode);
        if ("iast".equals(mode)) {
            loader = new IastLoader(agentArgs[0]);
        } else if ("rasp".equals(mode)) {
            loader = new OpenRaspLoader(agentArgs[0]);
        } else if ("soft-patch".equals(mode)) {
            loader = new SoftPatchLoader(agentArgs[0]);
        } else {
            loader = new IastLoader(agentArgs[0]);
        }
        loader.premain(agentArgs.length == 2 ? agentArgs[1] : null, inst);
    }

    /**
     * install agent with attach
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    public static void agentmain(String args, Instrumentation inst) {
        String[] agentArgs = parseArgs(args);
        String mode = System.getProperty("agent.mode", "iast");
        ILoader loader = null;
        LogUtils.info("OpenAgent mode: " + mode);
        if ("iast".equals(mode)) {
            loader = new IastLoader(agentArgs[0]);
        } else if ("rasp".equals(mode)) {
            loader = new OpenRaspLoader(agentArgs[0]);
        } else if ("soft-patch".equals(mode)) {
            loader = new SoftPatchLoader(agentArgs[0]);
        } else {
            loader = new IastLoader(agentArgs[0]);
        }
        loader.agentmain(agentArgs.length == 2 ? agentArgs[1] : null, inst);
    }

    /**
     * build command line args
     *
     * @param opt     short param
     * @param longOpt 长参数名
     * @param desc    参数描述
     * @return 参数对象
     */
    public static Option build(String opt, String longOpt, String desc) {
        return Option.builder(opt).longOpt(longOpt).hasArg().desc(desc).build();
    }

    /**
     * 解析 args 参数
     */
    public static String[] parseArgs(String args) {
        return args.split("&", 2);
    }

    /**
     * 获取
     */

}
