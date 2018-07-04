package h5app.comxiaomi.h5app.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

import h5app.comxiaomi.h5app.R;


/**
 * 作者：dell or Xiaomi Li<br>
 * 时间： 2018/3/31<br>
 * 内容：MVP架构基础类<br>
 * 最后修改：
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private ImmersionBar mImmersionBar;//设置状态栏

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseAppManager.getInstance().addActivity(this);
        //这里可以用一个view来放一个布局   也可以用一个布局id来当一个布局
        if (setLayout() instanceof View) {
            if (((View) setLayout()) != null) {
                setContentView((View) setLayout());
                creatView(savedInstanceState);
            } else {
                throw new RuntimeException("setLayout type must be view or int !");
            }
        } else if (setLayout() instanceof Integer) {
            if (((Integer) setLayout()) != 0) {
                setContentView((Integer) setLayout());
                creatView(savedInstanceState);
            } else {
                throw new RuntimeException("setLayout type must be view or int !");
            }
        }

        if (translucentStatusBar()) {//设置透明(隐藏)状态栏
            //透明状态栏，不写默认透明色
            mImmersionBar = ImmersionBar.with(this).transparentStatusBar();
        } else {
            mImmersionBar = ImmersionBar.with(this)
                    .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                    .statusBarColor(R.color.default_color);
        }
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
    }

    private void creatView(@Nullable Bundle savedInstanceState) {
        initData();
        initView(savedInstanceState);
    }

    /**
     * 设置布局/View
     *
     * @return
     */
    protected abstract Object setLayout();

    /**
     * 初始化Data
     */
    protected abstract void initData();


    /**
     * 初始化控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 时候隐藏顶部状态栏
     *
     * @return
     */
    protected abstract boolean translucentStatusBar();


    /**
     * 解除引用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            //必须调用该方法，防止内存泄漏
            mImmersionBar.destroy();
        }
        BaseAppManager.getInstance().removeActivity(this);
    }
}
