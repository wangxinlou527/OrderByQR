package com.jydp.obqr.net;

/**
 * 父类view层
 */
public interface BaseView {

    void toShowLoading();

    void toHiddenLoading();

    void requestFail(Throwable e);

    void requestFailMsg(String msg);

}
