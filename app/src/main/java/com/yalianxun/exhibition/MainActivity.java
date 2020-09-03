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
    private RecyclerView recyclerView;
    private final String[] tags = {"firstFragment","partyBuild","societyService","volunteer","charity","policyGuide","pension","hospital","life","enterprise","myCommunity","wisdom"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] titles = {"首页   ", " 党建与村居务公开 ", " 社工服务 ", " 志愿者服务 ", " 公益慈善 "," 政策与办事指南 ", " 养老服务 "," 医疗卫生 "," 生活服务 "," 企业服务 "," 我的社区 "," 智慧家庭 "};
        recyclerView = findViewById(R.id.recycler);
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
                    Objects.requireNonNull(linearLayoutManager.findViewByPosition(oldPosition + 1)).requestFocus();
                }
                if(key !=null &&key.equals("left") && oldPosition >0){
                    Objects.requireNonNull(linearLayoutManager.findViewByPosition(oldPosition - 1)).requestFocus();
                }
                if(key !=null &&key.equals("top")&& oldItemView != null){
                    oldItemView.requestFocus();
                }

                if(key != null && key.contains("click"))
                    skipView(key.substring(6));
            }
        }, myIntentFilter);

    }

    private void initFragments(){
        beginTransaction = getSupportFragmentManager().beginTransaction();

        for (String tag: tags) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            if(fragment != null) {
                beginTransaction.hide(fragment);
            }
        }
        beginTransaction.commit();
    }

    private void modItemState(View v, int position, boolean hasFocus){
        TextView tv = v.findViewById(R.id.title);
        View line = v.findViewById(R.id.line);
        boolean increase = position > oldPosition;
        boolean same = position == oldPosition;
        if(hasFocus){
            if(oldItemView != null && !oldItemView.equals(v)){
                TextView textView = oldItemView.findViewById(R.id.title);
                textView.setTextColor(Color.parseColor("#666666"));
            }
            tv.setTextColor(Color.parseColor("#199ED8"));
            line.setVisibility(View.VISIBLE);
            CommonUtils.setViewZoomIn(v,1.2f);
//            if(position == 0 && oldPosition != position){
//                showMyFragment("firstFragment");
//            }else if(position == 1 && oldPosition != position){
//                showMyFragment("partyBuild");
//            }else if(position == 2 && oldPosition != position){
//                showMyFragment("societyService");
//            }else if(position == 3 && oldPosition != position)
//                showMyFragment("volunteer");
//            else if(position == 4 && oldPosition != position)
//                showMyFragment("charity");
//            else if(position == 5 && oldPosition != position)
//                showMyFragment("policyGuide");
//            else if(position == 6 && oldPosition != position)
//                showMyFragment("pension");
//            else if(position == 7 && oldPosition != position)
//                showMyFragment("hospital");
//            else if(position == 8 && oldPosition != position)
//                showMyFragment("life");
            if(oldPosition != position)
                showMyFragment(tags[position]);
            oldPosition = position;
        }else {
            oldItemView = v;
            line.setVisibility(View.INVISIBLE);
            CommonUtils.setViewZoomOut(v,1.2f);
        }
        int step;
        if(increase)
            step = oldPosition + 3;
        else
            step = oldPosition - 3;
        if(oldPosition > 2 && oldPosition < 9 && !same){
            recyclerView.smoothScrollToPosition(step);
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

    private void skipView(String value){
        Log.i("xph"," click label is : " + value);
    }
}
