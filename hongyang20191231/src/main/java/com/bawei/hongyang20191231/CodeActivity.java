package com.bawei.hongyang20191231;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.hongyang20191231.base.BaseActivity;
import com.bawei.hongyang20191231.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: HONGYANG
 * i.	（核心）在Activity创建的时候注册EventBus，在Activity销毁的时候注销注册
 */

public class CodeActivity extends BaseActivity {
    @BindView(R.id.keyword)
    EditText keyword;
    @BindView(R.id.btn_code)
    Button btnCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_send)
    Button tvSend;
    @BindView(R.id.obj_send)
    Button objSend;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        CodeUtils.init(this);

        //i.	（核心）在Activity创建的时候注册EventBus，在Activity销毁的时候注销注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        ivCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(ivCode, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(CodeActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(CodeActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }
        });
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_code_layout;
    }

    @OnClick(R.id.btn_code)
    //生成二维码.使用自己的名字生成二维码并展示
    public void onViewClicked() {
        if (keyword.getText().toString().length() == 0) {
            Toast.makeText(this, "关键字不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = CodeUtils.createImage(keyword.getText().toString(), 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        ivCode.setImageBitmap(bitmap);
    }


    //ii.	（核心）点击图2中的微信的时候发送EventBus吐司微信，点击QQ的时候发送EventBus吐司QQ
    @OnClick({R.id.tv_send, R.id.obj_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                EventBus.getDefault().post("微信");
                break;
            case R.id.obj_send:
                EventBus.getDefault().post("QQ");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getString(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getobj(EditText editText) {
        Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
