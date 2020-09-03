package com.yalianxun.exhibition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.baidu.mapapi.map.MapView;

import java.util.Calendar;
import java.util.TimeZone;

public class CommonSecondaryActivity extends FragmentActivity {
    private TextView currentTime;
    public static final int UPDATE_TIME = 0;
    private MapView mMapView = null;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == UPDATE_TIME) {
                updateTime();
                handler.sendEmptyMessageDelayed(UPDATE_TIME, 60000);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_secondary);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView textView = findViewById(R.id.common_title);
        textView.setText(title);
        currentTime = findViewById(R.id.current_time);
        TextView location = findViewById(R.id.location);
        location.setText("南新村");
        handler.sendEmptyMessage(UPDATE_TIME);
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    private void updateTime(){
        Calendar calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String month = addZero(String.valueOf(calendars.get(Calendar.MONTH) + 1));
        String day = addZero(String.valueOf(calendars.get(Calendar.DATE)));
        String hour = String.valueOf(calendars.get(Calendar.HOUR));
        String minute = addZero(String.valueOf(calendars.get(Calendar.MINUTE)));
        boolean isAm = calendars.get(Calendar.AM_PM) == Calendar.AM;
        String time_quantum = isAm?"上午":"下午";
        String date_time = month + "月" + day + "日" + " " + time_quantum + hour + ":" + minute;
        currentTime.setText(date_time);
    }

    public void back(View view) {
        finish();
    }

    public void goSetting(View view) {
        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

    @Override
    public void finish() {
        super.finish();
        handler.removeCallbacksAndMessages(null);
    }

    private String addZero(String string){
        if(string.length() < 2)
            string = "0" + string;
        return string;
    }
}
