package com.zdk.recycleviewpagertest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zdk.recycleviewpagertest.R;
import com.zdk.recycleviewpagertest.view.PageGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */

public class MyAdapter extends PageGridView.PagingAdapter<MyVH> implements PageGridView.OnItemClickListener {
    List<String> mData = new ArrayList<>();
    private Context context;
    private int width;
    public MyAdapter(Context context,List<String> data,int width) {
        this.context=context;
        this.mData.addAll(data);
        this.width=width;
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = width;
        params.width = width;
        view.setLayoutParams(params);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        if(TextUtils.isEmpty(mData.get(position))){
            holder.icon.setVisibility(View.GONE);
        }else{
            holder.icon.setVisibility(View.VISIBLE);
        }
        holder.tv_title.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public Object getEmpty() {
        return "";
    }

    @Override
    public void onItemClick(PageGridView pageGridView, int position) {
        String gridview = "";
//        if (pageGridView == pageGridView) {
//            gridview = "第一个GridView";
//        }
//        if (pageGridView == pageGridView2) {
//            gridview = "第二个GridView";
//        }
//        if (pageGridView == pageGridView3) {
//            gridview = "第三个GridView";
//        }

        Toast.makeText(context, gridview + " 第" + (position + 1) + "个item 被点击" + " 值：" + mData.get(position), Toast.LENGTH_SHORT).show();
    }
}

class MyVH extends RecyclerView.ViewHolder {
    public TextView tv_title;
    public ImageView icon;
    public MyVH(View itemView) {
        super(itemView);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        icon= (ImageView) itemView.findViewById(R.id.icon);
    }
}
