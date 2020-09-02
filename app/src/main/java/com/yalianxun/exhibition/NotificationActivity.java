package com.yalianxun.exhibition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.yalianxun.exhibition.adapter.NewsAdapter;
import com.yalianxun.exhibition.beans.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends FragmentActivity {
    private List<NewsBean> listViewData = new ArrayList<>();
    private View lastView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        WebView webView = findViewById(R.id.content_web);
        webView.setBackgroundColor(0);
        webView.loadUrl("https://baijiahao.baidu.com/s?id=1595700515789334333&wfr=spider&for=pc");
        initData();
        final ListView listView = findViewById(R.id.list_view);
        NewsAdapter adapter = new NewsAdapter(listViewData,this);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("xph","position " + position);
                if(lastView != null){
                    lastView.setBackgroundResource(R.drawable.blue_frame_unselected);
                }
                lastView = view;
                view.setBackgroundResource(R.drawable.blue_frame_selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("xph","nothing ");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NotificationActivity.this,"human!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(){
        NewsBean newsBean = new NewsBean();
        newsBean.setTitle("蜂蜜的功效");
        newsBean.setTimestamp("08-15");
        newsBean.setOriginalUrl("https://baijiahao.baidu.com/s?id=1612532540849539614&wfr=spider&for=pc");
        listViewData.add(newsBean);
        newsBean = new NewsBean();
        newsBean.setTitle("茶道的艺术");
        newsBean.setTimestamp("07-15");
        newsBean.setOriginalUrl("https://baijiahao.baidu.com/s?id=1595700515789334333&wfr=spider&for=pc");
        listViewData.add(newsBean);
        newsBean = new NewsBean();
        newsBean.setTitle("蜂蜜的功效");
        newsBean.setTimestamp("08-15");
        newsBean.setOriginalUrl("https://baijiahao.baidu.com/s?id=1612532540849539614&wfr=spider&for=pc");
        listViewData.add(newsBean);
        newsBean = new NewsBean();
        newsBean.setTitle("茶道的艺术");
        newsBean.setTimestamp("07-15");
        newsBean.setOriginalUrl("https://baijiahao.baidu.com/s?id=1595700515789334333&wfr=spider&for=pc");
        listViewData.add(newsBean);
    }
    public void back(View view) {
        finish();
    }
}
