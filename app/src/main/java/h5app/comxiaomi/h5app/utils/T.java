package h5app.comxiaomi.h5app.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * 类描述：Toast工具类<br>
 * 作  者：XiaomiLi<br>
 * 时  间：2016/10/11.<br>
 * 修改备注：<br>
 */
public class T {
    static Toast toast;

    /**
     * 短提示
     *
     * @param mContext 上下文
     * @param msg      提示内容
     */
    public static void shortToast(Context mContext, String msg) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(mContext, msg + "", Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 长提示
     *
     * @param mContext 上下文
     * @param msg      提示内容
     */
    public static void longToast(Context mContext, String msg) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(mContext, msg + "", Toast.LENGTH_LONG);
        toast.show();
    }
}
