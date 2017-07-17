package com.wu.test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button send;
    private EditText editText;
    private MyAdapter myAdapter;
    private List<Msg> msgs;
    private RecyclerView recyclerView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        msgs = new ArrayList<>();
        send = (Button) findViewById(R.id.send);
        editText = (EditText) findViewById(R.id.send_msg);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        getData();
        myAdapter = new MyAdapter(msgs);
        recyclerView.setAdapter(myAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (!"".equals(msg)) {
                    Msg msg1 = new Msg(msg, Msg.MSG_SEND);
                    msgs.add(msg1);
                    myAdapter.notifyItemInserted(msgs.size()-1);
                    recyclerView.scrollToPosition(msgs.size()-1);
                    editText.setText("");
                }
            }
        });

    }

    private void getData() {
        Msg msg = new Msg("hello! ",Msg.MSG_RECEIVED);
        msgs.add(msg);
        Msg msg1 = new Msg("what?",Msg.MSG_SEND);
        msgs.add(msg1);
        Msg msg2 = new Msg("Would you mind to  making to friend with me?",Msg.MSG_RECEIVED);
        msgs.add(msg2);
    }
}
