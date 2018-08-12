package com.zdk.recycleviewpagertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zdk.recycleviewpagertest.adapter.MyAdapter;
import com.zdk.recycleviewpagertest.view.MyPageIndicator;
import com.zdk.recycleviewpagertest.view.PageGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int width;
    int screenWidth;
    List<String> data = new ArrayList<>();
    {
        for (int i = 1; i <= 17; i++) {
            data.add(i + "");
        }
    }
    PageGridView pageGridView, pageGridView2, pageGridView3;
    MyPageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageIndicator = (MyPageIndicator) findViewById(R.id.pageindicator);
        width = getResources().getDisplayMetrics().widthPixels / 4;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        pageGridView = (PageGridView) findViewById(R.id.pagingGridView);
        pageGridView2 = (PageGridView) findViewById(R.id.pagingGridView2);
        pageGridView3 = (PageGridView) findViewById(R.id.pagingGridView3);

        MyAdapter adapter1 = new MyAdapter(this, data, width);
        MyAdapter adapter2 = new MyAdapter(this, data, width);
        MyAdapter adapter3 = new MyAdapter(this, data, width);
        pageGridView.setAdapter(adapter1);
        pageGridView2.setAdapter(adapter2);
        pageGridView3.setAdapter(adapter3);
        pageGridView.setOnItemClickListener(adapter1);
        pageGridView2.setOnItemClickListener(adapter2);
        pageGridView3.setOnItemClickListener(adapter3);

        //设置分页指示器
        pageGridView2.setPageIndicator(pageIndicator);

    }


    int scrollX = 0;
    boolean isAuto = false;
    int Target = 0;
    public class MyScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.i("zzz", "onScrollStateChanged state=" + newState + " isAuto=" + isAuto);
            // recyclerView.smoothScrollBy(10,0);
            if (newState == 0) {
                if (!isAuto) {
                    int p = scrollX / screenWidth;
                    int offset = scrollX % screenWidth;
                    if (offset > screenWidth / 2) {
                        p++;
                    }
                    Target = p * screenWidth;
                    isAuto = true;
                    recyclerView.smoothScrollBy(Target - scrollX, 0);
                }
            } else if (newState == 2) {
                isAuto = false;
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            scrollX += dx;
            Log.i("zzz", "onScrolled dx=" + dx + " scrollX=" + scrollX);
        }

    }

}
