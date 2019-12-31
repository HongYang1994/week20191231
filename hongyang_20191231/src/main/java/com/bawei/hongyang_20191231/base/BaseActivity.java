package com.bawei.hongyang_20191231.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * Date: 2019/12/31
 * Time: 9:10
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

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int bindLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (presenter != null) {
            presenter.detach();
        }
    }
}
