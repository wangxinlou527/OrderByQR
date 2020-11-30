package com.jydp.obqr.netnavigator;

import com.jydp.obqr.entity.ErrorEntity;

/**
 * 网络回调
 *
 * @param <T> 数据返回的类型
 */
public abstract class NetNavigator<T> {
    public abstract void success(T data);

    public abstract void fail(String error);

    /**
     * 开始获取数据
     *
     * @param title dialog或者其他开始获取数据的提示信息
     */
    public abstract void start(String title);

    /**
     * 结束
     */
    public abstract void end();

    public abstract String getTitle();

    /**
     * 重新登录
     */
    public void needSignIn() {

    }
}