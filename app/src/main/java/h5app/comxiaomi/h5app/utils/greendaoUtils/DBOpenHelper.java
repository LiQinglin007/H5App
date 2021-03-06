package h5app.comxiaomi.h5app.utils.greendaoUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



import org.greenrobot.greendao.database.Database;

import h5app.comxiaomi.h5app.bean.DaoMaster;
import h5app.comxiaomi.h5app.bean.WebUrlBeanDao;

/**
 * @author 小东
 * @version v1.0
 * @date 2017/2/28 10:00
 * @detail 数据库升级
 */
public class DBOpenHelper extends DaoMaster.OpenHelper {
    private static final String TAG = DBOpenHelper.class.getSimpleName();

    public DBOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory) {
        super(context, dbName, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //这里不用判断版本，如果不更新不会走onUpgrade方法
        //直接把所有的表都放来就好，不用管那个改了那个没改
        MigrationHelper.getInstance().migrate(db, true, WebUrlBeanDao.class);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //这里有一个bug    高版本---》低版本  高版本里边的表不会被删掉
        MigrationHelper.getInstance().migrate(wrap(db), false, WebUrlBeanDao.class);
    }
}
