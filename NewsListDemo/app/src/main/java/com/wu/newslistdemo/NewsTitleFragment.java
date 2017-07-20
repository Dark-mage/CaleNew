package com.wu.newslistdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wu on 2017/7/20.
 */

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        NewsAdapter newsAdapter = new NewsAdapter(getNewsData());
        recyclerView.setAdapter(newsAdapter);
        return view;
    }
    private List<News> getNewsData(){
        List<News> list = new ArrayList<>();
        for (int i=1;i<51;i++){
            News news = new News();
            news.setNewsTitle("this is title"+i);
            news.setNewsContent(getRandomString("this is content"));
            list.add(news);
        }
        return list;
    }
    /**
     * 获取随机累加字符串
     * @param str
     * @return
     */
    private String getRandomString(String str){
        Random random = new Random();
        int len = random.nextInt(20) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <len ; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断是否为双页
        if(getActivity().findViewById(R.id.content_layout)!=null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
    }
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewsList;

        public NewsAdapter(List<News> mNewsList) {
            this.mNewsList = mNewsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.news_content_frag);
                        newsContentFragment.reFresh(news.getNewsTitle(),news.getNewsContent());
                    }else{
                        NewsContentActivity.startAction(getActivity(), news.getNewsTitle(), news.getNewsContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.textView.setText(news.getNewsTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.recycler_item);
            }
        }
    }
}
