package com.jydp.obqr.netnavigator;


import com.jydp.obqr.entity.ErrorEntity;
import com.jydp.obqr.util.SingleLiveEvent;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.FffCallback;
import com.jydp.obqr.viewmodel.ffCallback;

public abstract class SimpleNetNavigator<T> extends NetNavigator<T> {
    //success方法我们最好回调回去,所以不用实现
    //利用LiveData来显示信息
    private SingleLiveEvent<String> snackbarEvent;
    private SingleLiveEvent<String> dialogEvent;
    private Callback callback;
    private ffCallback ffcallback;
    private FffCallback fffCallback;

    protected SimpleNetNavigator(ffCallback ffcallback) {
        this.callback=callback;
    }

    protected SimpleNetNavigator(SingleLiveEvent<String> snackbarEvent, SingleLiveEvent<String> dialogEvent, Callback callback) {
        this.snackbarEvent = snackbarEvent;
        this.dialogEvent = dialogEvent;
        this.callback=callback;
    }
    protected SimpleNetNavigator(SingleLiveEvent<String> snackbarEvent, SingleLiveEvent<String> dialogEvent, ffCallback ffcallback) {
        this.snackbarEvent = snackbarEvent;
        this.dialogEvent = dialogEvent;
        this.ffcallback=ffcallback;
    }

    protected SimpleNetNavigator(SingleLiveEvent<String> snackbarEvent, SingleLiveEvent<String> dialogEvent, FffCallback fffcallback) {
        this.snackbarEvent = snackbarEvent;
        this.dialogEvent = dialogEvent;
        this.fffCallback=fffcallback;
    }

    @Override
    public void success(T data) {
        if (callback != null) {
            callback.success();
        }
    }

    @Override
    public void fail(final String error) {
        //加上空判断
        if (snackbarEvent != null)
            snackbarEvent.setValue(error);
    }

    @Override
    public void start(String title) {
        //我这个类型的开始了,就是显示一个dialog
        if (dialogEvent != null)
            dialogEvent.setValue(title);
    }

    @Override
    public void end() {
        if (dialogEvent != null)
            dialogEvent.call();
    }
}
