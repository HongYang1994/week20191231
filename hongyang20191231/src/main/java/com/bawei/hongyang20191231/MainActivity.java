package com.bawei.hongyang20191231;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bawei.hongyang20191231.adapter.HomeAdapter;
import com.bawei.hongyang20191231.api.Api;
import com.bawei.hongyang20191231.base.BaseActivity;
import com.bawei.hongyang20191231.contract.IHomeContract;
import com.bawei.hongyang20191231.entity.HomeEntity;
import com.bawei.hongyang20191231.presenter.HomePresenter;

import org.w3c.dom.Text;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {
    //i.	（核心）在Activity中注册ButterKnife绑定控件和事件
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_01)
    TextView textView;


    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        presenter.getData(Api.BASE_URL);
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new GridLayoutManager(this,1));
        //ii.	（核心）点击图1中的红色文字“点击这里分享给朋友”跳转到图2，
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CodeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void succsee(HomeEntity homeEntity) {
        HomeAdapter homeAdapter = new HomeAdapter(this,homeEntity.getRanking());
        rv.setAdapter(homeAdapter);
    }

    @Override
    public void failure(Throwable throwable) {

    }

}
