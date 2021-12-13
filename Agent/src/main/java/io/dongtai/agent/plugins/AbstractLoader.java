package io.dongtai.agent.plugins;

import io.dongtai.agent.LogUtils;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author owefsad
 */
public class AbstractLoader implements ILoader {

    private final String agentPath;
    private final String premainClass;
    private final String agentmainClass;

    public AbstractLoader(String agentPath, String premainClass, String agentmainClass) {
        this.agentPath = agentPath;
        this.premainClass = premainClass;
        this.agentmainClass = agentmainClass;
    }

    /**
     * install agent with premain
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    @Override
    public void premain(String args, Instrumentation inst) {
        //
        try {
            File raspJar = new File(this.agentPath);
            ClassLoader loader = AbstractLoader.class.getClassLoader();
            Class moduleClass;
            if (loader instanceof URLClassLoader) {
                Method method = Class.forName("java.net.URLClassLoader").getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(loader, raspJar.toURI().toURL());
                loader.loadClass(premainClass)
                        .getDeclaredMethod("premain", String.class, Instrumentation.class)
                        .invoke(null, args, inst);
            }
        } catch (ClassNotFoundException e) {
            LogUtils.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            LogUtils.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LogUtils.error(e.getMessage());
        } catch (InvocationTargetException e) {
            LogUtils.error(e.getMessage());
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
    }

    /**
     * install agent with attach
     *
     * @param args boot args [namespace,token,ip,port,prop]
     * @param inst inst
     */
    @Override
    public void agentmain(String args, Instrumentation inst) {
        try {
            File raspJar = new File(this.agentPath);
            ClassLoader loader = AbstractLoader.class.getClassLoader();
            Class moduleClass;
            if (loader instanceof URLClassLoader) {
                Method method = Class.forName("java.net.URLClassLoader").getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(loader, raspJar.toURI().toURL());
                loader.loadClass(agentmainClass)
                        .getDeclaredMethod("agentmain", String.class, Instrumentation.class)
                        .invoke(null, args, inst);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
