package com.bawei.hongyang_20191231;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bawei.hongyang_20191231.base.BaseActivity;
import com.bawei.hongyang_20191231.contract.IHomeContract;
import com.bawei.hongyang_20191231.presenter.HomePresenter;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IModel {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return 0;
    }

    @Override
    public void getData(String url, IHomeContract.IModelCallback iModelCallback) {

    }
}
