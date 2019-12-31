package com.bawei.hongyang20191231.utils;

import android.os.Handler;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Android Studio.
 * User: HONGYANG
 */

//iv.	（核心）使用单例模式二次封装OKHTTP
public class OkhttpUtils {
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    //，添加OKHTTP日志拦截器
    private OkhttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static class OkhttpHoder {
        private static OkhttpUtils okhttpUtils = new OkhttpUtils();
    }

    public static OkhttpUtils getInstance() {
        return OkhttpHoder.okhttpUtils;
    }

    //v.	（核心）封装OKHTTP的get方法
    public void doGet(String url, OkhttpCallback okhttpCallback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okhttpCallback != null) {
                    okhttpCallback.failure(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.succsee(result);
                        }
                    });
                }
            }
        });
    }

    public interface OkhttpCallback {
        void succsee(String result);

        void failure(Throwable throwable);
    }
}
