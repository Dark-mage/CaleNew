package com.wu.test1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wu on 2017/7/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {
    private List<Msg> msgs;

    public MyAdapter(List<Msg> msgs) {
        this.msgs = msgs;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Msg msg = msgs.get(position);
        if(msg.getType()==Msg.MSG_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftView.setText(msg.getMsg());
        }else if(msg.getType()==Msg.MSG_SEND){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightView.setText(msg.getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftView;
        TextView rightView;
        public Viewholder(View itemView) {
            super(itemView);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            leftView = (TextView) itemView.findViewById(R.id.left_msg);
            rightView = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }
}
