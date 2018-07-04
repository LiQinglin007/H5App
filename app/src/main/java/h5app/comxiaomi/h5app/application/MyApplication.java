package h5app.comxiaomi.h5app.application;

import android.app.Application;

import h5app.comxiaomi.h5app.utils.greendaoUtils.DBManager;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 9:18<br>
 * UpdateDescription：<br>
 */
public class MyApplication extends Application {

    private final static String DBName = "WebApp.db";

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.getInstance().init(getApplicationContext(), DBName);
    }
}
