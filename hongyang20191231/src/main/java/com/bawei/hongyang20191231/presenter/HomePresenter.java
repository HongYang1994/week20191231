package com.bawei.hongyang20191231.presenter;

import com.bawei.hongyang20191231.base.BasePresenter;
import com.bawei.hongyang20191231.contract.IHomeContract;
import com.bawei.hongyang20191231.entity.HomeEntity;
import com.bawei.hongyang20191231.model.HomeModel;

/**
 * Created by Android Studio.
 * User: HONGYANG
 */

public class HomePresenter extends BasePresenter<HomeModel, IHomeContract.IView> implements IHomeContract.IPresenter {
    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    @Override
    public void getData(String url) {
        model.getData(url, new IHomeContract.IModel.IModelCallback() {
            @Override
            public void succsee(HomeEntity homeEntity) {
                getView().succsee(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }
}
