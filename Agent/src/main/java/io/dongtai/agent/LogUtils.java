package io.dongtai.agent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author owefsad
 */
public class LogUtils {

    public static void info(String msg) {

        System.out.println(prefix("INFO") + "io.dongtai.agent  : " + msg);
    }

    public static void error(String msg) {
        System.err.println(prefix("ERROR") + "io.dongtai.agent  : " + msg);
    }

    public static String prefix(String level) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        return sdf.format(date) + " " + level + " " + Thread.currentThread().getId() + " ";
    }
}
