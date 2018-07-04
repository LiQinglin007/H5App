package h5app.comxiaomi.h5app.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import h5app.comxiaomi.h5app.R;
import h5app.comxiaomi.h5app.bean.WebUrlBean;
import h5app.comxiaomi.h5app.bean.WebUrlBeanManager;
import h5app.comxiaomi.h5app.utils.BaseActivity;
import h5app.comxiaomi.h5app.utils.LogUtils;
import h5app.comxiaomi.h5app.utils.T;

/**
 * Description:对某个地址的编辑 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 10:03<br>
 * UpdateDescription：<br>
 */
public class EditWebBeanActivity extends BaseActivity implements View.OnClickListener {
    Toolbar mToolbar;
    TextView mSaveTv;
    EditText mTitleEd;
    EditText mContentEd;
    CheckBox mCheckBox;
    int type = 1;//1:添加  2：修改
    long webBeanId = -1;

    @Override
    protected Object setLayout() {
        return R.layout.activity_editwebbean;
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
        if (type == 2) {
            webBeanId = getIntent().getLongExtra("webBeanId", -1);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.edit_toolbar);
        mSaveTv = findViewById(R.id.edit_web_save);
        mTitleEd = findViewById(R.id.edit_web_title);
        mContentEd = findViewById(R.id.edit_web_content);
        mCheckBox = findViewById(R.id.edit_check);

        mSaveTv.setOnClickListener(this);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        mToolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.nav_icon_return));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setView();
    }


    private void setView() {
        if (type == 1) {//新建
            mToolbar.setTitle("新建书签");
        } else {
            mToolbar.setTitle("编辑书签");
            WebUrlBean webUrlBean = WebUrlBeanManager.queryById(webBeanId);
            if (webUrlBean != null) {
                mTitleEd.setText(webUrlBean.getWebUrlTitle() + "");
                mContentEd.setText(webUrlBean.getWebUrlContent() + "");
                mCheckBox.setChecked(webUrlBean.getDefaultUrl());
            }
        }
    }

    @Override
    protected boolean translucentStatusBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_web_save:
                String title = mTitleEd.getText().toString();
                String content = mContentEd.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    T.shortToast(EditWebBeanActivity.this, "书签名称或书签地址不能为空");
                    return;
                }
                WebUrlBean mWebBean = new WebUrlBean();
                mWebBean.setWebUrlTitle(title);
                mWebBean.setWebUrlContent(content);
                mWebBean.setDefaultUrl(mCheckBox.isChecked());
                if (mWebBean.getDefaultUrl()) {
                    //先把之前的默认地址改成非默认地址
                    WebUrlBeanManager.updateDefault();
                }
                if (type == 1) {//新建
                    mWebBean.setCreatTime(new Date().getTime());
                    WebUrlBeanManager.addBean(mWebBean);
                    T.shortToast(EditWebBeanActivity.this, "添加成功");
                } else {
                    mWebBean.setWebUrlId(webBeanId);
                    WebUrlBeanManager.updateBeanData(mWebBean);
                    T.shortToast(EditWebBeanActivity.this, "修改成功");
                }
                finish();
                break;
            default:
                break;
        }
    }
}
