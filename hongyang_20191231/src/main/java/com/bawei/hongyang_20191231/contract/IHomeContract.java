package com.bawei.hongyang_20191231.contract;

import com.bawei.hongyang_20191231.base.IBaseModel;
import com.bawei.hongyang_20191231.base.IBaseView;
import com.bawei.hongyang_20191231.entity.HomeEntity;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * Date: 2019/12/31
 * Time: 9:18
 */

public interface IHomeContract {
    interface IModel extends IBaseModel {
        void getData(String url,IModelCallback iModelCallback);
    }

    interface IModelCallback {
        void success(HomeEntity homeEntity);

        void failure(Throwable throwable);
    }
    interface IView extends IBaseModel, IBaseView {
        void success(HomeEntity homeEntity);

        void failure(Throwable throwable);
    }
    interface IPresenter {
        void getData(String url);
    }
}
