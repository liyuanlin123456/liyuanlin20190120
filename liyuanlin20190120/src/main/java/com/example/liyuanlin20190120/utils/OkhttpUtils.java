package com.example.liyuanlin20190120.utils;

import android.os.Handler;

import com.example.liyuanlin20190120.net.OkhttpCallBack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    private Handler handler=new Handler();
    private OkHttpClient okHttpClient;
    private static OkhttpUtils mInstance;
    public OkhttpUtils() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置超时时间
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public void doPost(String apiUrl, HashMap<String,String> params, final OkhttpCallBack okhttpCallBack){
        FormBody.Builder formBody = new FormBody.Builder();
        //判断参数是否为空  进行参数的拼接
        if (params!=null){
            for (Map.Entry<String,String> p:params.entrySet()){
                formBody.add(p.getKey(),p.getValue());
            }
        }
        Request request = new Request.Builder().url(apiUrl).post(formBody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallBack.onFailUre("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                int code = response.code();
                if (200==code){
                    if (okhttpCallBack!=null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallBack.onSuccess(result);
                            }
                        });
                    }
                }
            }
        });
    }

    //单例模式
    public static OkhttpUtils getmInstance() {
        if (mInstance==null){
            synchronized (OkhttpUtils.class){
                if (mInstance==null){
                    mInstance=new OkhttpUtils();
                }
            }
        }
        return mInstance;
    }

    public void okHttpCancel(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
