package h5app.comxiaomi.h5app.ui;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import h5app.comxiaomi.h5app.R;
import h5app.comxiaomi.h5app.adapter.IconAdapter;
import h5app.comxiaomi.h5app.event.MessageEvent;
import h5app.comxiaomi.h5app.utils.BaseActivity;
import h5app.comxiaomi.h5app.utils.T;
import h5app.comxiaomi.h5app.utils.recycler.OnItemClickListener;

/**
 * Description:设置logo <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 14:43<br>
 * UpdateDescription：<br>
 */
public class SettingIconActivity extends BaseActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    IconAdapter mIconAdapter;

    ArrayList<Integer> mIconList = new ArrayList<>();


    @Override
    protected Object setLayout() {
        return R.layout.activity_setting_icon;
    }

    @Override
    protected void initData() {


        mIconList.add(R.mipmap.logo);
        mIconList.add(R.mipmap.fluttrt_icon);
        mIconList.add(R.mipmap.ic_launcher);
    }


    int clickNumber = 0;
    int positionNumber = -1;


    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.setting_icon_toolbar);
        mRecyclerView = findViewById(R.id.setting_icon_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mIconAdapter = new IconAdapter(SettingIconActivity.this);
        mIconAdapter.setList(mIconList);
        mRecyclerView.setAdapter(mIconAdapter);

        mIconAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (positionNumber == position) {
                    clickNumber = 0;
                    positionNumber = -1;
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setType(MessageEvent.setLauncherTypr);
                    messageEvent.setNumberMesssage(position);
                    EventBus.getDefault().post(messageEvent);
                } else {
                    clickNumber++;
                    positionNumber = position;
                    T.shortToast(SettingIconActivity.this, "再次点击设置启动图标");
                }
            }
        });
    }



    @Override
    protected boolean translucentStatusBar() {
        return false;
    }
}
