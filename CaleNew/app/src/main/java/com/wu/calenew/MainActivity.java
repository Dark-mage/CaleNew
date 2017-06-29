package com.wu.calenew;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_divide;
    private Button btn_mul;
    private Button btn_minus;
    private Button btn_add;
    private Button btn_point;
    private Button btn_del;
    private Button btn_clear;
    private Button btn_eql;
    private EditText editView;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_eql= (Button) findViewById(R.id.btn_equ);
        btn_del= (Button) findViewById(R.id.btn_DEL);
        btn_clear= (Button) findViewById(R.id.btn_C);
        editView = (EditText) findViewById(R.id.view);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_eql.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }
    public void onClick(View v) {
        String str=editView.getText().toString();
        switch(v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(flag){
                    str="";
                    editView.setText("");
                }
                editView.setText(str+((Button)v).getText());
                flag=false;
                break;
            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_mul:
            case R.id.btn_divide:
                if(flag){
                    str="";
                    editView.setText("");
                }
                editView.setText(str+" "+((Button)v).getText()+" ");
                flag=false;
                break;
            case R.id.btn_equ:
                flag = true;
                int space=str.indexOf(" ");
                String arg1 = str.substring(0, space);
                String op=str.substring(space+1,space+2);
                String arg2=str.substring(space+3);
                if(space==-1){
                    break;
                }
                if(arg1==null||"".equals(arg1)||arg2==null||"".equals(arg2)){
                    editView.setText("");
                    break;
                }
                double a1 = Double.parseDouble(arg1);
                double a2 = Double.parseDouble(arg2);
                double r=0;
                if("+".equals(op)){
                    r=a1+a2;
                }else if("-".equals(op)){
                    r=a1-a2;

                }else if("×".equals(op)){
                    r=a1*a2;

                }else if("÷".equals(op)){
                    r=a2!=0?a1/a2:0;
                }
                editView.setText(r+"");
                break;
            case R.id.btn_DEL:
                editView.setText("");
                break;
            case R.id.btn_C:
                if(str!=null&&!"".equals(str)){
                    String s=str.substring(0,str.length()-1);
                    editView.setText(s);
                }
                break;
        }
    }
}
