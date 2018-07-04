package h5app.comxiaomi.h5app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import h5app.comxiaomi.h5app.R;
import h5app.comxiaomi.h5app.adapter.UrlListAdapter;
import h5app.comxiaomi.h5app.bean.WebUrlBean;
import h5app.comxiaomi.h5app.bean.WebUrlBeanManager;
import h5app.comxiaomi.h5app.utils.BaseActivity;
import h5app.comxiaomi.h5app.utils.T;
import h5app.comxiaomi.h5app.utils.recycler.OnItemClickListener;
import h5app.comxiaomi.h5app.utils.recycler.OnSideslipClick;

/**
 * Description:书签列表页面 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 9:23<br>
 * UpdateDescription：<br>
 */
public class UrlListActivity extends BaseActivity {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    UrlListAdapter mUrlListAdapter;
    ArrayList<WebUrlBean> mBeanArrayList = new ArrayList<>();

    @Override
    protected Object setLayout() {
        return R.layout.activity_url_list;
    }

    @Override
    protected void initData() {
    }

    int ClickNumber = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.urlList_toolbar);
        mRecyclerView = findViewById(R.id.urlList_recycler);
        mUrlListAdapter = new UrlListAdapter(UrlListActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(mUrlListAdapter);
        mUrlListAdapter.setList(mBeanArrayList);
        mUrlListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //防止前边页面连续点击误触
                if (ClickNumber == 0) {
                    ClickNumber++;
                    return;
                }
                Intent mIntent = new Intent(UrlListActivity.this, MainActivity.class);
                mIntent.putExtra("webBeanUrl", mBeanArrayList.get(position).getWebUrlContent());
                startActivity(mIntent);
                finish();
            }
        });
        getData();

        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_add:
                        Intent mIntent = new Intent(UrlListActivity.this, EditWebBeanActivity.class);
                        mIntent.putExtra("type", 1);
                        startActivity(mIntent);
                        break;
                    case R.id.toolbar_update_list:
                        getData();
                        break;
//                    case R.id.toolbar_setting_icon:
//                        startActivity(new Intent(UrlListActivity.this, SettingIconActivity.class));
//                        break;
                }
                return true;
            }
        });


        mUrlListAdapter.setOnSideslipClick(new OnSideslipClick() {
            @Override
            public void OnDeleteClick(int position) {//删除按钮
                boolean b = WebUrlBeanManager.deleteById(mBeanArrayList.get(position).getWebUrlId());
                if (b) {
                    mBeanArrayList.remove(position);
                    mUrlListAdapter.setList(mBeanArrayList);
                }
            }

            @Override
            public void OnEditClick(int position) {//编辑按钮
                Intent mIntent = new Intent(UrlListActivity.this, EditWebBeanActivity.class);
                mIntent.putExtra("type", 2);
                mIntent.putExtra("webBeanId", mBeanArrayList.get(position).getWebUrlId());
                startActivity(mIntent);
            }
        });
    }

    @Override
    protected boolean translucentStatusBar() {
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_meun, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        List<WebUrlBean> webUrlBeans = WebUrlBeanManager.loadAll();
        if (webUrlBeans != null && webUrlBeans.size() != 0) {
            mBeanArrayList.clear();
            mBeanArrayList.addAll(webUrlBeans);
            mUrlListAdapter.setList(mBeanArrayList);
        } else {
            T.shortToast(UrlListActivity.this, "暂无书签,快去添加吧");
        }
    }
}
