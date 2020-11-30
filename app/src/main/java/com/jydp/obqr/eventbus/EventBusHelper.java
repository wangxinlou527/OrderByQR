package com.jydp.obqr.eventbus;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.ArrayMap;
import androidx.annotation.RequiresApi;


import com.jydp.myapplication.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/**
 * @author: yeq
 * @date: 2019/11/13
 *    desc   : EventBus 辅助类
 */
public final class EventBusHelper {

    // EventBus 索引类
    private static final SubscriberInfoIndex SUBSCRIBE_INDEX = new MyEventBusIndex();

    // 这个类是否需要注册 EventBus
    @SuppressLint("NewApi")
    private static final ArrayMap<String, Boolean> SUBSCRIBE_EVENT = new ArrayMap<>();

    // 不允许被外部实例化
    private EventBusHelper() {}

    /**
     * 初始化 EventBus
     */
    public static void init() {
        EventBus.builder()
                .ignoreGeneratedIndex(false) // 使用 Apt 插件
                .addIndex(SUBSCRIBE_INDEX) // 添加索引类
                .installDefaultEventBus(); // 作为默认配置
    }

    /**
     * 注册 EventBus
     */
    public static void register(Object subscriber) {
        if (canSubscribeEvent(subscriber)) {
            if (!EventBus.getDefault().isRegistered(subscriber)) {
                EventBus.getDefault().register(subscriber);
            }
        }
    }

    /**
     * 反注册 EventBus
     */
    public static void unregister(Object subscriber) {
        if (canSubscribeEvent(subscriber) && EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }
    /**
     * 发送事件消息
     *
     * @param event
     */
    public static void post(EventMessage event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件消息
     *
     * @param event
     */
    public static void postSticky(EventMessage event) {
        EventBus.getDefault().postSticky(event);
    }
    /**
     * 判断是否使用了 EventBus 注解
     *
     * @param subscriber                 被订阅的类
     */
    private static boolean canSubscribeEvent(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        // 这个 Class 类型有没有遍历过
        Boolean result = SUBSCRIBE_EVENT.get(clazz.getName());
        if (result != null) {
            // 有的话直接返回结果
            return result;
        }

        // 没有的话进行遍历
        while (clazz != null) {
            // 如果索引集合中有这个 Class 类型的订阅信息，则这个类型的对象都需要注册 EventBus
            if (SUBSCRIBE_INDEX.getSubscriberInfo(clazz) != null) {
                // 这个类需要注册 EventBus
                result = true;
                clazz = null;
            } else {
                String clazzName = clazz.getName();
                // 跳过系统类（忽略 java. javax. android. androidx. 等开头包名的类）
                if (clazzName.startsWith("java") || clazzName.startsWith("android")) {
                    clazz = null;
                } else {
                    // 往上查找
                    clazz = clazz.getSuperclass();
                }
            }
        }
        // 这个类不需要注册 EventBus
        if (result == null) {
            result = false;
        }
        SUBSCRIBE_EVENT.put(subscriber.getClass().getName(), result);
        return result;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventBusHelper helper) {
        // 占位，只为了能生成 MyEventBusIndex 索引类
        // 如果项目中已经有用到 @Subscribe 去注解方法，这个方法可以直接删除
    }
}
