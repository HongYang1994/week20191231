package com.bawei.hongyang20191231.model;

import com.bawei.hongyang20191231.contract.IHomeContract;
import com.bawei.hongyang20191231.entity.HomeEntity;
import com.bawei.hongyang20191231.utils.OkhttpUtils;
import com.google.gson.Gson;

/**
 * Created by Android Studio.
 * User: HONGYANG
 */

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getData(String url, IModelCallback iModelCallback) {
        OkhttpUtils.getInstance().doGet(url, new OkhttpUtils.OkhttpCallback() {
            @Override
            public void succsee(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result,HomeEntity.class);
                iModelCallback.succsee(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                iModelCallback.failure(throwable);
            }
        });
    }
}
