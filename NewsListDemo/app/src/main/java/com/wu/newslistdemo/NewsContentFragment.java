package com.wu.newslistdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wu on 2017/7/20.
 */

public class NewsContentFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    /**
     * 刷新数据
     * @param newsTitle
     * @param newsContent
     */
    public void reFresh(String newsTitle,String newsContent){
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.visibility_layout);
        linearLayout.setVisibility(View.VISIBLE);
        TextView titleView = (TextView) view.findViewById(R.id.news_title);
        TextView contentView = (TextView) view.findViewById(R.id.news_content);
        titleView.setText(newsTitle);
        contentView.setText(newsContent);
    }

}
