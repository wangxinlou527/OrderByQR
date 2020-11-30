package com.jydp.obqr.netnavigator;

import com.jydp.obqr.entity.ErrorEntity;

/**
 * 这个类是用来处理在NetNavigator里面的方法在执行之前需要处理的一些公共方法
 * @param <T>
 */
public class NetNavigatorWrapper<T> extends NetNavigator<T> {
    /**
     * 这个就是需要被代理的对象
     */
    private NetNavigator<T> netNavigator;

    /**
     * 从构造方法中传入你需要被代理的对象
     * @param netNavigator 被代理的NetNavigator
     */
    public NetNavigatorWrapper(NetNavigator<T> netNavigator) {
        this.netNavigator = netNavigator;
    }
    //就是在执行这个方法,如果不是代理对象
    @Override
    public void success(T data) {
        //将数据传给外层的netNavigator
        netNavigator.success(data);
    }
    @Override
    public void fail(String error) {
        netNavigator.fail(error);
    }

    @Override
    public void start(String title) {
        netNavigator.start(title);
    }

    @Override
    public void end() {
        netNavigator.end();
    }

    @Override
    public String getTitle() {
        return netNavigator.getTitle();
    }
}
