package cn.pumpkin.log;

import com.orhanobut.logger.Logger;
/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/19 14:22
 * @des:
 * @see {@link }
 */

public class ZHLog {
    private static final int VERBOSE = 5;
    private static final int DEBUG = 4;
    private static final int INFO = 3;
    private static final int WARN = 2;
    private static final int ERROR = -1;

    private static int LOG_LEVEL = 6;

    public static void v(String tag, String msg) {
        if (LOG_LEVEL > VERBOSE) {
            Logger.v(tag, msg);
        }
    }

    /**
     * 可以显示数据集合
     * */
    public static void v(Object obj) {
        if (LOG_LEVEL > VERBOSE) {
            Logger.d(obj);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_LEVEL > DEBUG) {
            Logger.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_LEVEL > INFO) {
            Logger.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_LEVEL > WARN) {
            Logger.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_LEVEL > ERROR) {
            Logger.e(tag, msg);
        }
    }

    public static void json(String json){
        Logger.json(json);
    }

    public static void xml(String xml){
        Logger.xml(xml);
    }

}
