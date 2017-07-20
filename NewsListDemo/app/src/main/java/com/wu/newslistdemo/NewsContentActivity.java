package com.wu.newslistdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        TextView titleView = (TextView) findViewById(R.id.news_title);
        TextView contentView = (TextView) findViewById(R.id.news_content);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.visibility_layout);
        linearLayout.setVisibility(View.VISIBLE);
        String newsTitle = getIntent().getStringExtra("newsTitle");
        String newsContent = getIntent().getStringExtra("newsContent");
        titleView.setText(newsTitle);
        contentView.setText(newsContent);

    }

    /**
     *启动NewsContentActivity
     * @param context
     * @param newsTitle
     * @param newsContent
     */
    public static void startAction(Context context,String newsTitle,String newsContent){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("newsTitle", newsTitle);
        intent.putExtra("newsContent", newsContent);
        context.startActivity(intent);
    }
}
