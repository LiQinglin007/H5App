package h5app.comxiaomi.h5app.ui;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import h5app.comxiaomi.h5app.R;
import h5app.comxiaomi.h5app.bean.WebUrlBean;
import h5app.comxiaomi.h5app.bean.WebUrlBeanManager;
import h5app.comxiaomi.h5app.event.MessageEvent;
import h5app.comxiaomi.h5app.utils.BaseActivity;
import h5app.comxiaomi.h5app.utils.BaseAppManager;
import h5app.comxiaomi.h5app.utils.LogUtils;
import h5app.comxiaomi.h5app.utils.MyCacheWebView;
import h5app.comxiaomi.h5app.utils.T;
import ren.yale.android.cachewebviewlib.CacheWebView;
import ren.yale.android.cachewebviewlib.utils.NetworkUtils;

public class MainActivity extends BaseActivity {
    MyCacheWebView mCacheWebView;
    String webBeanUrl = "";
    private long urlListStartTime = 0;
    private long urlListEndTime = 0;
    private int urlListNumber = 0;

    ComponentName deComponentName;
    ComponentName componentName;
    ComponentName smComponentName;
    ArrayList<ComponentName> ComponentNameList = new ArrayList<>();
    String smarthomeName = "h5app.comxiaomi.h5app.smarthome";
    String deName = "h5app.comxiaomi.h5app.ui.UrlListActivity";
    String fluttrtName = "h5app.comxiaomi.h5app.home";

    @Override
    protected Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        deComponentName = new ComponentName(MainActivity.this, deName);
        componentName = new ComponentName(MainActivity.this, fluttrtName);
        smComponentName = new ComponentName(MainActivity.this, smarthomeName);

        ComponentNameList.add(deComponentName);
        ComponentNameList.add(componentName);
        ComponentNameList.add(smComponentName);
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView(Bundle savedInstanceState) {
        mCacheWebView = findViewById(R.id.main_web);
        WebSettings webSettings = mCacheWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDefaultTextEncodingName("UTF-8");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(mCacheWebView, true);
        }
        if (NetworkUtils.isConnected(mCacheWebView.getContext())) {
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        } else {
            webSettings.setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        mCacheWebView.setOnTouchScreenListener(new MyCacheWebView.OnTouchScreenListener() {
            @Override
            public void onTouchScreen() {
                //按下
                toUrlList();
            }

            @Override
            public void onReleaseScreen() {//抬起

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    private void load() {
        webBeanUrl = getIntent().getStringExtra("webBeanUrl");
        if (TextUtils.isEmpty(webBeanUrl)) {
            List<WebUrlBean> webUrlBeans = WebUrlBeanManager.loadAll();
            if (webUrlBeans != null && webUrlBeans.size() != 0) {
                webBeanUrl = webUrlBeans.get(0).getWebUrlContent();
            }
            if (TextUtils.isEmpty(webBeanUrl)) {
                T.shortToast(MainActivity.this, "请先配置书签");
                startActivity(new Intent(MainActivity.this, UrlListActivity.class));
            } else {
                WebUrlBean webUrlBean = WebUrlBeanManager.loadDefault();
                if (webUrlBean != null) {
                    webBeanUrl = webUrlBean.getWebUrlContent();
                }
                mCacheWebView.loadUrl(webBeanUrl);
            }
        } else {
            mCacheWebView.loadUrl(webBeanUrl);
        }
    }

    private void toUrlList() {
        urlListNumber++;
        if (urlListNumber == 1) {
            urlListStartTime = System.currentTimeMillis();
            LogUtils.Loge("====start====" + urlListStartTime);
        }
        if (urlListNumber == 3) {
            urlListEndTime = System.currentTimeMillis();
            LogUtils.Loge("====end====" + urlListEndTime);
        }
        if (urlListNumber >= 3) {
            if ((urlListEndTime - urlListStartTime) < 1000) {
                startActivity(new Intent(MainActivity.this, UrlListActivity.class));
            }
            urlListNumber = 0;
        }
        if ((System.currentTimeMillis() - urlListStartTime) > 1500) {
            urlListNumber = 0;
            urlListStartTime = System.currentTimeMillis();
            LogUtils.Loge("====超过1000ms=====");
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mCacheWebView.canGoBack()) {
                mCacheWebView.goBack();
                return true;
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    BaseAppManager.getInstance().clear();
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected boolean translucentStatusBar() {
        return false;
    }

    private void changeLuncher(ComponentName componentName) {
        PackageManager pm = getPackageManager();
        for (ComponentName componentName1 : ComponentNameList) {
            //去除旧图标，不去除的话会出现2个App图标
            pm.setComponentEnabledSetting(componentName1,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
        //显示新图标
        pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        //Intent 重启 Launcher 应用
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
        Toast.makeText(this, "桌面图标已更换", Toast.LENGTH_LONG).show();
        BaseAppManager.getInstance().clear();
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setLauncher(MessageEvent messageEvent) {
        switch (messageEvent.getNumberMesssage()) {
            case 0:
                changeLuncher(smComponentName);
                break;
            case 1:
                changeLuncher(componentName);
                break;
            default:
                changeLuncher(deComponentName);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCacheWebView != null) {
            mCacheWebView.clearCache();
        }
        EventBus.getDefault().unregister(this);
    }
}
