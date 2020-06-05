package com.yalianxun.exhibition.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yalianxun.exhibition.R;
import com.yalianxun.exhibition.utils.CommonUtils;

import java.util.Objects;


public class SecondFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_view_style_second, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageView imageView = Objects.requireNonNull(getView()).findViewById(R.id.imageView);
        CommonUtils.setImageViewRadiusCorner(getContext(),imageView);
    }
}
