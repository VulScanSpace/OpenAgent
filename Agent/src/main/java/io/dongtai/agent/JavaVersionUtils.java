package io.dongtai.agent;

import java.util.Properties;

/**
 * @author hengyunabc 2018-11-21
 */
public class JavaVersionUtils {

    private static final String VERSION_PROP_NAME = "java.specification.version";
    private static final String JAVA_VERSION_STR = System.getProperty(VERSION_PROP_NAME);

    private JavaVersionUtils() {
    }

    public static String javaVersionStr() {
        return JAVA_VERSION_STR;
    }

    public static String javaVersionStr(Properties props) {
        return (null != props) ? props.getProperty(VERSION_PROP_NAME) : null;
    }

}
