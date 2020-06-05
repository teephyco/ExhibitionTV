package com.yalianxun.exhibition.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yalianxun.exhibition.R;


public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private String[] mTitles;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener = null;
    private OnItemFocusChangeListener onItemFocusChangeListener =null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemFocusChangeListener(OnItemFocusChangeListener onItemFocusChangeListener) {
        this.onItemFocusChangeListener = onItemFocusChangeListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemFocusChangeListener {
        void onItemFocusChange(View v, int position,boolean hasFocus);
    }

    public CustomRecyclerAdapter(String[] mTitles, Context context) {
        this.mTitles = mTitles;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.item_recycler,

                viewGroup, false);

        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        String str = mTitles[position];
        viewHolder.title.setText(str);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onItemFocusChangeListener != null) {
                    onItemFocusChangeListener.onItemFocusChange(v, position,hasFocus);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        if (mTitles == null) {
            return 0;
        }
        return mTitles.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        View v;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            v = view.findViewById(R.id.line);
        }

    }


}
