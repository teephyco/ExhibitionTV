package com.yalianxun.exhibition.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yalianxun.exhibition.R;
import com.yalianxun.exhibition.beans.NewsBean;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> listViewData;
    private Context mContext;

    public NewsAdapter(List<NewsBean> listViewData, Context mContext) {
        this.listViewData = listViewData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listViewData.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item;
        ViewHolder vh;
        if(convertView == null){
            item = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
            vh = new ViewHolder();
            vh.titleTv = item.findViewById(R.id.title);
            vh.dateTv = item.findViewById(R.id.timestamp);
            item.setTag(vh);
        }else {
            item = convertView;
            vh = (ViewHolder) item.getTag();
        }
        NewsBean newsBean = listViewData.get(position);
        if(newsBean != null){
            vh.titleTv.setText(newsBean.getTitle());
            vh.dateTv.setText(newsBean.getTimestamp());
        }

        return item;
    }

    static class ViewHolder {
        TextView titleTv;
        TextView dateTv;
    }
}
