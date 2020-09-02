package com.yalianxun.exhibition.widget;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yalianxun.exhibition.NotificationActivity;
import com.yalianxun.exhibition.R;
import com.yalianxun.exhibition.utils.CommonUtils;

import androidx.fragment.app.Fragment;

import java.util.Objects;


public class FirstFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_view_style_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageView imageView = Objects.requireNonNull(getView()).findViewById(R.id.imageView);
        CommonUtils.setImageViewRadiusCorner(getContext(),imageView);
        View view = Objects.requireNonNull(getView()).findViewById(R.id.society);
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NotificationActivity.class));
            }
        });
    }
}
