package com.yalianxun.exhibition;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yalianxun.exhibition.adapter.CustomRecyclerAdapter;
import com.yalianxun.exhibition.utils.CommonUtils;

import java.util.Objects;

public class MainActivity extends FragmentActivity {
    private int oldPosition = -1;
    private FragmentTransaction beginTransaction;
    private Fragment oldFragment;
    private View oldItemView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] titles = {"首页   ", " 社工服务 ", " 志愿者服务 ", " 公益慈善 ", " 党建与村居务公开 ", " 养老服务 "," 政策与办事指南 "," 居家控制 "};
        final RecyclerView recyclerView = findViewById(R.id.recycler);
        final CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(titles,this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter.setOnItemFocusChangeListener(new CustomRecyclerAdapter.OnItemFocusChangeListener() {
            @Override
            public void onItemFocusChange(View v, int position, boolean hasFocus) {
                modItemState(v,position,hasFocus);
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        initFragments();
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("broadcast_test");

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String key = intent.getStringExtra("key");

                if(key !=null &&key.equals("right") && oldPosition < (titles.length - 1)){

                    if(oldPosition == titles.length/2){
                        recyclerView.smoothScrollToPosition(titles.length - 1);
                    }
                    Objects.requireNonNull(linearLayoutManager.findViewByPosition(oldPosition + 1)).requestFocus();
                }
                if(key !=null &&key.equals("left") && oldPosition >0){
                    if(oldPosition == titles.length/2){
                        recyclerView.smoothScrollToPosition(0);
                    }
                    Objects.requireNonNull(linearLayoutManager.findViewByPosition(oldPosition - 1)).requestFocus();
                }
                if(key !=null &&key.equals("top")&& oldItemView != null){
                    oldItemView.requestFocus();
                }
            }
        }, myIntentFilter);

    }

    private void initFragments(){
        beginTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("firstFragment");
        Fragment second = getSupportFragmentManager().findFragmentByTag("second");
        Fragment third = getSupportFragmentManager().findFragmentByTag("third");
        if(fragment != null) {
            beginTransaction.hide(fragment);
        }
        if(second !=null)
            beginTransaction.hide(second);
        if(third != null)
            beginTransaction.hide(third);

        beginTransaction.commit();
    }

    private void modItemState(View v, int position, boolean hasFocus){
        TextView tv = v.findViewById(R.id.title);
        View line = v.findViewById(R.id.line);
        if(hasFocus){
            if(oldItemView != null && !oldItemView.equals(v)){
                TextView ot = oldItemView.findViewById(R.id.title);
                ot.setTextColor(Color.parseColor("#666666"));
            }
            tv.setTextColor(Color.parseColor("#199ED8"));
            line.setVisibility(View.VISIBLE);
            CommonUtils.setViewZoomIn(v,1.2f);
            if(position == 0 && oldPosition != position){
                showMyFragment("firstFragment");
            }else if(position == 1 && oldPosition != position){
                showMyFragment("second");
            }else if(position == 2 && oldPosition != position){
                showMyFragment("third");
            }
            oldPosition = position;
        }else {
            oldItemView = v;
            line.setVisibility(View.INVISIBLE);
            CommonUtils.setViewZoomOut(v,1.2f);
        }

    }

    private void showMyFragment(String string){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(string);
        if(fragment != null) {
            if(oldFragment != null)
                beginTransaction.hide(oldFragment);
            beginTransaction.show(fragment);
            beginTransaction.setCustomAnimations(R.anim.slide_in,R.anim.slide_out).commitNow();
            oldFragment = fragment;
        }
    }
}
