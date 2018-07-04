package h5app.comxiaomi.h5app.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-04<br>
 * Time: 9:03<br>
 * UpdateDescription：<br>
 */
public class MyCacheWebView extends CacheWebView {

    private OnTouchScreenListener onTouchScreenListener;

    public MyCacheWebView(Context context) {
        super(context);
    }

    public MyCacheWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCacheWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onTouchScreen();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onReleaseScreen();
        }

        return super.onTouchEvent(event);
    }

    public interface OnTouchScreenListener {
        void onTouchScreen();

        void onReleaseScreen();
    }

    public void setOnTouchScreenListener(OnTouchScreenListener onTouchScreenListener) {
        this.onTouchScreenListener = onTouchScreenListener;
    }

}
