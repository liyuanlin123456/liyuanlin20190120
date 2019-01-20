package com.example.liyuanlin20190120.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liyuanlin20190120.R;

public class RegActivity extends AppCompatActivity {

    private EditText name;
    private EditText pwd;
    private EditText qpwd;
    private Button btn_reg;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        getSupportActionBar().hide();
        //获取页面控件的id
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        qpwd = findViewById(R.id.qpwd);
        btn_reg = findViewById(R.id.btn_reg);

        //获得sharedpreference
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        //点击事件
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namee = name.getText().toString();
                String pwdd = pwd.getText().toString();
                String qpwdd = qpwd.getText().toString();
                if (namee!=null && !name.equals("") && pwdd!=null && !pwdd.equals("") && qpwdd!=null && !qpwdd.equals("")){
                    if (pwdd.equals(qpwdd)){
                        Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        edit.putString("name",namee);
                        edit.putString("pwd",qpwdd);
                        edit.commit();
                    }else{
                        Toast.makeText(RegActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
