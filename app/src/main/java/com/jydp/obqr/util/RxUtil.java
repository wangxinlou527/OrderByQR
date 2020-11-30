package com.jydp.obqr.util;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.jydp.obqr.entity.ErrorEntity;
import com.jydp.obqr.entity.XiadanEntity;
import com.jydp.obqr.net.IResponse;
import com.jydp.obqr.netnavigator.NetNavigator;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOError;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;


public class RxUtil {
    private static final String TAG = RxUtil.class.getSimpleName();
    private static Class<ErrorEntity> clazz;


    public static <T> void ioMain(Observable<T> observable, final NetNavigator<T> netNavigator) {
//        Log.e("TAG", "进入RX----------------");
        //当我们获取数据的时候,这些方法的回调,如果是代理对象,执行success
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    //订阅观察者
                    @Override
                    public void onSubscribe(Disposable d) {
                        netNavigator.start(netNavigator.getTitle());

                    }
                    //接收传递的对象
                    @Override
                    public void onNext(T data) {
                        //请求成功
                        netNavigator.success(data);
                        Log.e("TAG", "success()-----请求1------"+data.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求失败
//                        ToastUtil.showToast("e.getMessage()");
                       // Log.e("TAG", "e.getMessage()-----请求失败1------"+e.getMessage() );

                            if (e instanceof HttpException) {

                                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                                try {
                                    if (responseBody != null) {
                                        ErrorEntity respon = toEntity(responseBody.charStream(), ErrorEntity.class);
                                        //                                       ErrorEntity respon = toEntity(new StringReader(responseBody.charStream()),ErrorEntity.class);
                                        //                                       Log.e("TAG", "e.getMessage()-----请求失败6------"+ respon.getMessage());
                                        switch (respon.getStatus_code()) {
                                            case 422:
                                                Log.d("-----------Error1", respon.getErrors().toString());
                                                String str = respon.getErrors().toString();
                                                int start = str.indexOf("[\"");
                                                int end = str.indexOf("\"]");
                                                if (start != -1 && end != -1) {
                                                    netNavigator.fail(respon.getErrors().toString().substring(start + 2, end));
                                                }
                                                break;

                                            default:
                                                netNavigator.fail(respon.getMessage());
                                                break;
                                        }
//        IResponse respon = JsonUtils.toEntity(response.body().charStream(), clazz);
                                        responseBody.close();
                                    }

                                } catch (Exception e1) {

                                }

                                netNavigator.end();
                            }
                    }

                    @Override
                    public void onComplete() {
                        netNavigator.end();

                    }
                });
    }

    public static <T> T toEntity(Reader is, Class<T> classOfT) {
        JsonReader jsonReader = new JsonReader(is);
        return GSON.fromJson(jsonReader, classOfT);
    }

    private static final Gson GSON = new Gson();


}
