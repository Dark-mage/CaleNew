package com.wu.forceoffline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText accountView;
    private EditText passwordView;
    private Button loginBtn;
    private Button resetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }
    private void init(){
        accountView = (EditText) findViewById(R.id.account);
        passwordView = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        resetBtn = (Button) findViewById(R.id.reset);
    }

    /**
     * 判读是登陆还是重置
     * @param v
     */
    public void login(View v){
        String account = accountView.getText().toString();
        String password = passwordView.getText().toString();
        switch(v.getId()){
            case R.id.login://验证账号与密码是否正确
                if("a".equals(account)&&"a".equals(password)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "account or password is error", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reset:
                accountView.setText("");
                passwordView.setText("");
                break;
            default:
                break;
        }
    }
}
