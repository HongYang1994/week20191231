package com.bawei.hongyang20191231.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.hongyang20191231.CodeActivity;
import com.bawei.hongyang20191231.R;
import com.bawei.hongyang20191231.entity.HomeEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: HONGYANG
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH>{
    private Context context;
    private List<HomeEntity.RankingBean> list;

    public HomeAdapter(Context context, List<HomeEntity.RankingBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_item_layout,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Glide.with(context).load(list.get(position).getAvatar())
                //iv.	（复习）使用Glide圆形图片展示排行头像，并配置占位图和错误图
                .transform(new RoundedCorners(100))
                .error(R.drawable.ic_launcher_background)
                .into(holder.iv);
        holder.tv.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //给RecycleView设置点击事件，点击吐司名称
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            //ii.	（核心）在Adapter中注册ButterKnife绑定控件
            ButterKnife.bind(this,itemView);
        }
    }
}
