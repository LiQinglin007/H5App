package h5app.comxiaomi.h5app.utils.greendaoUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import h5app.comxiaomi.h5app.bean.DaoMaster;
import h5app.comxiaomi.h5app.bean.DaoSession;


/**
 * @author 小东
 * @version v1.0
 * @date 2017/2/28 10:00
 * @detail 数据库管理
 */
public class DBManager {
    private static final String TAG = DBManager.class.getSimpleName();

    private static DBManager instance;

    private DBManager() {
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private boolean mInited = false; //是否初始化过
    private Context mContext = null;//上下文对象
    /**
     * 数据库操作
     */
    private DaoMaster.OpenHelper mOpenHelper = null;
    private SQLiteDatabase mDatabase = null;
    private DaoMaster mDaoMaster = null;
    private DaoSession mDaoSession = null;

    /**
     * 初始化数据库
     *
     * @param context
     */
    public void init(Context context, String dbName) {
        if (!mInited || mContext == null) {
            this.mContext = context;
            DaoMaster.OpenHelper mOpenHelper = new DBOpenHelper(mContext, dbName, null);
            mDatabase = mOpenHelper.getWritableDatabase();
            mDaoSession = new DaoMaster(mDatabase).newSession();
        }
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
