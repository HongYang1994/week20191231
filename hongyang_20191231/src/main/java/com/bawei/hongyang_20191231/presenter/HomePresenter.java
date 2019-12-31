package com.bawei.hongyang_20191231.presenter;

import com.bawei.hongyang_20191231.base.BasePresenter;
import com.bawei.hongyang_20191231.contract.IHomeContract;
import com.bawei.hongyang_20191231.entity.HomeEntity;
import com.bawei.hongyang_20191231.model.HomeModel;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * Date: 2019/12/31
 * Time: 9:57
 */

public class HomePresenter extends BasePresenter<HomeModel, IHomeContract.IView> implements IHomeContract.IPresenter{
    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    @Override
    public void getData(String url) {
        model.getData(url, new IHomeContract.IModelCallback() {
            @Override
            public void success(HomeEntity homeEntity) {
                getView().success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }
}
