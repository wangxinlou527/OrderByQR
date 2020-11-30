package com.jydp.obqr.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.gyf.barlibrary.ImmersionBar;
import com.jydp.obqr.eventbus.EventBusHelper;

import org.jetbrains.annotations.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;


public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    public T binding;

    protected ImmersionBar mImmersionBar;
    private Unbinder mButterKnife;//View注解


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, getContentLayoutId(), container, false);
        ///        注册EventBus 接收数据
        EventBusHelper.register(this);
        //点击隐藏键盘
        binding.getRoot().setOnClickListener(v -> {
            Context context = getContext();
            if (context == null) {
                return;
            }
            if (null != getActivity().getCurrentFocus()) {
                /**
                 * 点击空白位置 隐藏软键盘
                 */
                InputMethodManager mInputMethodManager = (InputMethodManager) (context.getSystemService(INPUT_METHOD_SERVICE));
                if (mInputMethodManager != null)
                    mInputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });
        mButterKnife = ButterKnife.bind(getActivity());
        return binding.getRoot();
    }

    //获取到fragment的布局id
    public abstract int getContentLayoutId();



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        EventBusHelper.unregister(this);
        if (mButterKnife != null) mButterKnife.unbind();
    }


    protected abstract void viewCreate(Bundle savedInstanceState);


    @Override
    public void onStart() {
        super.onStart();
    }




}
