package com.example.liyuanlin20190120.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liyuanlin20190120.R;

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText pwd;
    private CheckBox checkBox;
    private Button btn_login;
    private Button btn_reg;
    private Button btn_qq;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取登录页面的控件id
        getSupportActionBar().hide();
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        checkBox = findViewById(R.id.checkBox);
        btn_login = findViewById(R.id.btn_login);
        btn_reg = findViewById(R.id.btn_reg);
        btn_qq = findViewById(R.id.btn_qq);
        //获取sharedpreferences
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        //获取保存的数据
        String login_namee = sharedPreferences.getString("login_name", null);
        String login_pwdd = sharedPreferences.getString("login_pwd", null);
        boolean checkboxx = sharedPreferences.getBoolean("checkbox", false);
        //判断是否选中
        if (checkboxx){
            checkBox.setChecked(checkboxx);
            name.setText(login_namee);
            pwd.setText(login_pwdd);
        }
        //获取保存的数据
        final String reg_name = sharedPreferences.getString("name", null);
        final String reg_pwd = sharedPreferences.getString("pwd", null);
        //设置点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String pwd1 = pwd.getText().toString();
                if (name1.equals(reg_name)) {
                    if (pwd1.equals(reg_pwd)) {
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "输入的密码错误", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"您输入的用户名未进行注册,请先注册",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,RegActivity.class));
                }
            }
        });
        //注册按钮的跳转
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
            }
        });
        //获取checkbox的选中转态
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String login_name = name.getText().toString();
                    String login_pwd = pwd.getText().toString();
                    if (login_name!=null || login_name.equals("") || login_pwd!=null || login_pwd.equals("")){
                        edit.putString("login_name",login_name);
                        edit.putString("login_pwd",login_pwd);
                        edit.putBoolean("checkbox",true);
                        edit.commit();
                    }
                }
            }
        });
    }
}
