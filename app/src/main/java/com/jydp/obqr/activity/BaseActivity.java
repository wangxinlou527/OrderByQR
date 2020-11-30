package com.jydp.obqr.activity;

import android.app.Activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.barlibrary.ImmersionBar;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.manager.ActivityManager;
import com.jydp.obqr.printer.DeviceConnFactoryManager;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity<VB extends ViewDataBinding> extends AppCompatActivity {


    protected ImmersionBar mImmersionBar;
    protected VB dataBinding;
    public BaseActivity getConrent;
    private Unbinder mButterKnife;//View注解

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///        注册EventBus 接收数据
        EventBusHelper.register(this);
        ActivityManager.getInstance().addActivity(this);//多语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            initImmersionBar();
        dataBinding = DataBindingUtil.setContentView(getChildActivity(), getLayoutId());
        mButterKnife = ButterKnife.bind(this);
    }

    protected abstract Activity getChildActivity();

    protected abstract int getLayoutId();


    /**
     * 初始化 mImmersionBar
     */
    protected void initImmersionBar() {

        if (mImmersionBar == null) {
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.init();
        }

    }

    @Override
    protected void onDestroy() {
        Log.d("----------","----onDestroy--");
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);//多语言
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        if (mButterKnife != null) mButterKnife.unbind();
        EventBusHelper.unregister(this);
//        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers() != null) {
//            DeviceConnFactoryManager.closeAllPort();
//        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (mInputMethodManager == null) {
                return false;
            }
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 获取点击事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
