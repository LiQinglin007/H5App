package h5app.comxiaomi.h5app.bean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import h5app.comxiaomi.h5app.utils.greendaoUtils.DBManager;

/**
 * Description:数据库操作类 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 10:25<br>
 * UpdateDescription：<br>
 */
public class WebUrlBeanManager {

    /**
     * 添加一条
     *
     * @param webUrlBean
     * @return 返回受影响行数
     */
    public static long addBean(WebUrlBean webUrlBean) {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        long insertNumber = webUrlBeanDao.insert(webUrlBean);
        return insertNumber;
    }

    /**
     * 根据主键查询
     *
     * @param WebUrlBeanId
     * @return
     */
    public static WebUrlBean queryById(Long WebUrlBeanId) {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        WebUrlBean mWebUrlBean = webUrlBeanDao.load(WebUrlBeanId);
        return mWebUrlBean;
    }


    /**
     * 根据主键删除
     *
     * @param WebUrlBeanId
     * @return true:  删除成功  false:删除失败
     */
    public static boolean deleteById(Long WebUrlBeanId) {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        webUrlBeanDao.deleteByKey(WebUrlBeanId);
        WebUrlBean webUrlBean = queryById(WebUrlBeanId);
        return webUrlBean == null ? true : false;
    }

    /**
     * 修改数据
     *
     * @param webUrlBean
     */
    public static void updateBeanData(WebUrlBean webUrlBean) {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        WebUrlBean urlBean = queryById(webUrlBean.getWebUrlId());
        if (urlBean != null) {
            webUrlBeanDao.update(webUrlBean);
        }
    }


    /**
     * 如果有就修改，没有就插入
     */
    public static List<WebUrlBean> loadAll() {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        QueryBuilder<WebUrlBean> webUrlBeanQueryBuilder = webUrlBeanDao.queryBuilder().orderDesc(WebUrlBeanDao.Properties.CreatTime);
        List<WebUrlBean> list = webUrlBeanQueryBuilder.list();
        return list;
    }

    /**
     * 查询默认地址
     *
     * @return
     */
    public static WebUrlBean loadDefault() {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        QueryBuilder<WebUrlBean> webUrlBeanQueryBuilder = webUrlBeanDao.queryBuilder().where(WebUrlBeanDao.Properties.DefaultUrl.eq(true));
        WebUrlBean webUrlBean = webUrlBeanQueryBuilder.unique();
        return webUrlBean;
    }

    /**
     * 把之前的默认地址修改成不默认的地址
     */
    public static void updateDefault() {
        WebUrlBeanDao webUrlBeanDao = DBManager.getInstance().getDaoSession().getWebUrlBeanDao();
        QueryBuilder<WebUrlBean> webUrlBeanQueryBuilder = webUrlBeanDao.queryBuilder().where(WebUrlBeanDao.Properties.DefaultUrl.eq(true));
        WebUrlBean webUrlBean = webUrlBeanQueryBuilder.unique();
        if (webUrlBean != null) {
            webUrlBean.setDefaultUrl(false);
            webUrlBeanDao.update(webUrlBean);
        }
    }

}
