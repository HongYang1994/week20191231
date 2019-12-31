package com.bawei.hongyang_20191231.model;

import com.bawei.hongyang_20191231.contract.IHomeContract;
import com.bawei.hongyang_20191231.entity.HomeEntity;
import com.bawei.hongyang_20191231.utils.OkhttpUtils;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * Date: 2019/12/31
 * Time: 9:36
 */

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getData(String url, IHomeContract.IModelCallback iModelCallback) {
        OkhttpUtils.getInstance().doGet(url, new OkhttpUtils.OkhttpCallback() {
            @Override
            public void success(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result,HomeEntity.class);
                iModelCallback.success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                iModelCallback.failure(throwable);
            }
        });
    }
}
