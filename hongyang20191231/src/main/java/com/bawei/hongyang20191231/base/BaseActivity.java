package com.bawei.hongyang20191231.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * ii.	（核心）封装Activity基类，在基类中封装初始化P层的方法，并在基类中解决MVP内存泄漏
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    Unbinder unbinder;
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder = ButterKnife.bind(this);

        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }

        initView();
        initData();

    }

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int bindLayoutId();

    //解决MVP内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (presenter!=null){
            presenter.detach();
        }
    }
}
