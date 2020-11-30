package com.jydp.obqr.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jydp.obqr.App;
import com.jydp.obqr.net.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitUtil {
    private static RetrofitUtil INSTANCE;
    public ApiService apiService;
    private static final String baserUrl = ApiService.baseUrl;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create(new Gson());
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();

    private RetrofitUtil() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(chain -> {
                    //添加head头部
                    // TODO: 2018/5/22 切换分支后需要将v2改为v1
                    Request request = chain.request().newBuilder()
                            .addHeader("Accept", "application/vnd.api-demo.v2+json")
                            .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                            .addHeader("Accept-Encoding","")
                            .addHeader("Connection","keep-alive")
                            .addHeader("Accept","*/*")
                            .addHeader("X-HB-Client-Type","ayb-android")
                            .addHeader("Content-Type","multipart/form-data; boundary=7db372eb000e2")
                            .build();
                    return chain.proceed(request);
                })

                .addNetworkInterceptor(new HttpLoggingInterceptor())
                //请求头添加token
                .addInterceptor(addHeaderInterceptor())
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baserUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//通过这句代码去设置一个解析工厂。--
                .addConverterFactory(gsonConverterFactory)
                .addConverterFactory(scalarsConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    /**
     * 获取到单例
     *
     * @return INSTANCE
     */
    public static RetrofitUtil getInstance() {

        if (INSTANCE == null) {
            synchronized (RetrofitUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitUtil();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * 添加统一的请求头
     *
     * @return
     */
    public Interceptor addHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                //在登录的情况下为每个请求的请求头里都添加Cookie
                if (!TextUtils.isEmpty(ISharedPreference.getInstance(App.app).getToken())) {
                    // token type  Bearer
                    builder.addHeader("Authorization", "Bearer" + ISharedPreference.getInstance(App.app).getToken());
                }
                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

}
