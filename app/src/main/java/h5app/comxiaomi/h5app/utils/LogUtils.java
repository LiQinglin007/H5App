package h5app.comxiaomi.h5app.utils;

import android.util.Log;

/**
 * 类描述：打印日志工具类<br>
 * 作  者：XiaomiLi<br>
 * 时  间：2017/5/9.<br>
 * 修改备注：<br>
 */
public class LogUtils {
    static boolean show = true;

    private static void setShow(boolean flag) {
        show = flag;
    }

    /**
     * 自定义TAG 日志
     * @param TAG
     * @param conetnt
     */
    public static void Loge(String TAG, String conetnt) {
        if (show) {
            Log.e(TAG, conetnt + "###");
        }
    }

    public static void Loge(String conetnt) {
        if (show) {
            Log.e("我是TAG", conetnt + "###");
        }
    }
}
