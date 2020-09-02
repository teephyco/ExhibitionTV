package com.yalianxun.exhibition.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.yalianxun.exhibition.R;

public class CommonUtils {
    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static void setViewZoomIn(View v, float scale){
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation animation = new ScaleAnimation(1.0f,scale,1.0f,scale,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(350);//动画效果时间
        animation.setFillAfter(true);
        animationSet.addAnimation(animation);
        animationSet.setFillAfter(true);
        v.clearAnimation();
        v.startAnimation(animationSet);
    }

    //对所有View都可执行的缩小动画
    public static  void setViewZoomOut(View v ,float scale){
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation animation = new ScaleAnimation(scale,1.0f,scale,1.0f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(350);//动画效果时间
        animation.setFillAfter(true);
        animationSet.addAnimation(animation);
        animationSet.setFillAfter(true);
        v.clearAnimation();
        v.startAnimation(animationSet);
    }

    public static  void setImageViewRadiusCorner(final Context context, final ImageView imageView){
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.app_icon_your_company)
                .into(new BitmapImageViewTarget(imageView){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(40); //设置圆角弧度
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
