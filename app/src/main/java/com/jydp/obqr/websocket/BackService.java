package com.jydp.obqr.websocket;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jydp.obqr.App;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.net.ApiService;
import com.jydp.obqr.util.ISharedPreference;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 *
 */
public class BackService extends Service {

    public Application application;

    public Context context;

    public static final int timeS = 3550000;

    public BackService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private InitSocketThread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
        new InitSocketThread().start();
        application = App.getInstance();
        context = App.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * 心跳检测时间
     */
    private static final long HEART_BEAT_RATE = 5 * 1000;//每隔7秒进行一次对长连接的心跳检测

    //    private final String WEBSOCKET_HOST_AND_PORT = "wss://qrorder.jydp.work/wss?authorization=Bearer "+ISharedPreference.getInstance(getApplication()).getToken()+"&channel=staff.merchant."+ISharedPreference.getInstance(getApplication()).getMerchant_id();//可替换为自己的主机名和端口号
    private String WEBSOCKET_HOST_AND_PORT;//可替换为自己的主机名和端口号
    private WebSocket mWebSocket;
    // 初始化socket

    private void initSocket() throws UnknownHostException, IOException {

        WEBSOCKET_HOST_AND_PORT = ApiService.wsUrl +  "wss?authorization=Bearer " + ISharedPreference.getInstance(getApplication()).getToken() + "&channel=staff.merchant." + ISharedPreference.getInstance(getApplication()).getMerchant_id();//可替换为自己的主机名和端口号
        Log.e("TAG", "WEBSOCKET_HOST_AND_PORT---------------"+WEBSOCKET_HOST_AND_PORT);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {//开启长连接成功的回调
                super.onOpen(webSocket, response);
                mWebSocket = webSocket;
                Log.e("websocket", "WEBSOCKET response "+response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {//接收消息的回调
                super.onMessage(webSocket, text);
                //收到服务器端传过来的消息text
                Log.e("websocket", "接收消息的回调2 " + text);

                try {
                    JSONObject jsonObject = JSON.parseObject(text);
//                    JSONObject jsonObject=new JSONObject(Boolean.parseBoolean(text));
                    String type = jsonObject.getString("type");
                    Log.e("websocket", "type " + type);
                    if (type.equals("4")) {
                        //消息EventMainBack
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_MESSAGE_RT_UPDATE));
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_MESSAGE_VOICE));
                    } else if (type.equals("2")) {
                        //是座位的
//                        EventBusHelper.post(new EventMessage(EventCode.EVENT_MESSAGE_VOICE));
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_CLEAR_SEAT));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                Log.e("websocket", "webSocket" + webSocket);
                Log.e("websocket", "bytes" + bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                Log.e("websocket", "onClosing:" + reason);
                Log.e("websocket", "code:" + code);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                Log.e("websocket", "webSocket:webSocket-" + webSocket);
                Log.e("websocket", "code:" + code);
                Log.e("websocket", "code:" + reason);

            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                super.onFailure(webSocket, t, response);
                //Request一旦build()之后，便不可修改
            }
        });
        client.dispatcher().executorService().shutdown();
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
    }


    class InitSocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                initSocket();
//                initSocket01();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long sendTime = 0L;
    // 发送心跳包
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        int i = 1;
        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                if (mWebSocket != null) {
//                    boolean isSuccess = mWebSocket.send("{\"type\":\"ping\"}");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                    boolean isSuccess = mWebSocket.send("{type:2000}");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                    if (!isSuccess) {//长连接已断开，
                        Log.d("websocket", "发送心跳包-------------长连接已断开");
                        if (ISharedPreference.getInstance(App.app).getTime() < (System.currentTimeMillis() - timeS)) {
                            if(i == 0){
                                EventBusHelper.post(new EventMessage(EventCode.EVENT_UPDATE_TOKEN));
                                i = 1;
                            }
                        }

                        mHandler.removeCallbacks(heartBeatRunnable);
                        mWebSocket.cancel();//取消掉以前的长连接
                        new InitSocketThread().start();//创建一个新的连接
                    } else {//长连接处于连接状态---
                        Log.d("websocket", "发送心跳包-------------长连接处于连接状态");
                        i = 0;
                    }
                }

                sendTime = System.currentTimeMillis();
            }
            mHandler.postDelayed(this, HEART_BEAT_RATE);//每隔一定的时间，对长连接进行一次心跳检测
        }
    };


    @Override
    public boolean stopService(Intent name) {
        Log.d("----------","onDestroy stopService");
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("----------","onDestroy myService");
        if(ISharedPreference.getInstance(getApplication()).getToken().equals("")) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }

    }

}
