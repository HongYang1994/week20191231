package com.bawei.hongyang20191231.contract;

import com.bawei.hongyang20191231.base.IBaseModel;
import com.bawei.hongyang20191231.base.IBaseView;
import com.bawei.hongyang20191231.entity.HomeEntity;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * iv.	（核心）使用契约统一管理MVP
 */

public interface IHomeContract {
    interface IModel extends IBaseModel {
        void getData(String url, IModelCallback iModelCallback);

        interface IModelCallback {
            void succsee(HomeEntity homeEntity);

            void failure(Throwable throwable);
        }
    }

    interface IView extends IBaseView {
        void succsee(HomeEntity homeEntity);

        void failure(Throwable throwable);
    }

    interface IPresenter {
        void getData(String url);
    }
}
