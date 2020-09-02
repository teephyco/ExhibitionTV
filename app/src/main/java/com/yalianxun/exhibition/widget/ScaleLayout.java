package com.yalianxun.exhibition.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yalianxun.exhibition.R;


public class ScaleLayout extends RelativeLayout {
    public boolean isKeep = false;
    private Context mContext;
    private Drawable original_bg;
    private float SCALE_MIN_VALUE = 1;
    private float SCALE_MAX_VALUE = 1.1f;

    public ScaleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSPRING_MAX_VALUE(float SPRING_MAX_VALUE) {
        this.SCALE_MAX_VALUE = SPRING_MAX_VALUE;
    }

    public ScaleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        this.setFocusableInTouchMode(true);
    }

    public ScaleLayout(Context context) {
        super(context);
    }

    public void scaleOut() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY",
                SCALE_MIN_VALUE, SCALE_MAX_VALUE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX",
                SCALE_MIN_VALUE, SCALE_MAX_VALUE);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY);
        animSet.setDuration(200);
        animSet.start();
    }

    public void scaleIn() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY",
                SCALE_MAX_VALUE, SCALE_MIN_VALUE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX",
                SCALE_MAX_VALUE, SCALE_MIN_VALUE);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY);
        animSet.setDuration(200);
        animSet.start();
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.i("xph"," background " + this.getBackground() + " info : " + this );
        if (isKeep)
            return;
        if (gainFocus) {
//            scaleOut();
            original_bg = this.getBackground();
            this.setBackground(mContext.getDrawable(R.drawable.selected_bolder));
        } else {
//            scaleIn();
            this.setBackground(original_bg);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("xph"," event " + getContext() + " keycode " + event.getKeyCode());
//        Log.i("xph","event " + event.getKeyCode() + "  left " + getLeft() + " right : " + getRight() + " width " + getWidth());
        int[] location = {0,0};
        getParent();
        getLocationOnScreen(location);

        if(event.getKeyCode() == 21 && location[0] == 20){
            if(event.getAction() == KeyEvent.ACTION_DOWN) {
                sendBroadcast("left");
            }
            return true;
        }
        //当到达最右侧是跳下一页
        if(event.getKeyCode() == 22 && (location[0] + getWidth()) == 1900){
            if(event.getAction() == KeyEvent.ACTION_DOWN) {
                sendBroadcast("right");
            }
            return true;
        }
        if(event.getKeyCode() == 19 && location[1] == 219){
            if(event.getAction() == KeyEvent.ACTION_DOWN) {
                sendBroadcast("top");
                return true;
            }
        }

        if(event.getKeyCode() == 66){
            if(event.getAction() == KeyEvent.ACTION_DOWN && this.getChildCount() > 2) {
                if(this.getChildAt(2) instanceof TextView){
                    TextView textView = (TextView)this.getChildAt(2);
                    sendBroadcast("click:" + textView.getText().toString());
                }
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    private void sendBroadcast(String string){
        Intent intent = new Intent();
        intent.putExtra("key", string);
        intent.setAction("broadcast_test");
        mContext.sendBroadcast(intent);
    }
}
