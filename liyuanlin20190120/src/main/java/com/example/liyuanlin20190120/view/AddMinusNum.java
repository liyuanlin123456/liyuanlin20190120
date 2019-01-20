package com.example.liyuanlin20190120.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liyuanlin20190120.R;

public class AddMinusNum extends LinearLayout {

    private TextView minus;
    private EditText ed_num;
    private TextView add;
    private int num;

    public AddMinusNum(Context context) {
        this(context,null);
    }

    public AddMinusNum(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusNum(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void init(Context context) {
        //获取控件
        View view = LayoutInflater.from(context).inflate(R.layout.addminus, this);
        minus = view.findViewById(R.id.minus);
        ed_num = view.findViewById(R.id.ed_num);
        add = view.findViewById(R.id.add);
        //设置监听为其赋值
        minus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                num = Integer.parseInt(ed_num.getText().toString());
                num--;
                if (num==0){
                    num=1;
                }
                numCallBack.getNum(num);
                ed_num.setText(num+"");
            }
        });
        //设置监听 为其赋值
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                numCallBack.getNum(num);
                ed_num.setText(num+"");
            }
        });
    }

    public void setNum(int num) {
        ed_num.setText(num+"");
    }

    private NumCallBack numCallBack;

    public void setNumCallBack(NumCallBack numCallBack) {
        this.numCallBack = numCallBack;
    }

    public interface NumCallBack{
        void getNum(int num);
    }
}
