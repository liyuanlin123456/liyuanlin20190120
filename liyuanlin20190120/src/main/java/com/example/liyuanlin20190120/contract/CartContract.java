package com.example.liyuanlin20190120.contract;

import com.example.liyuanlin20190120.net.RequestCallBack;

import java.util.HashMap;

public interface CartContract {
    //p层的方法
    public abstract class CartPresenter{
        public abstract void cart(HashMap<String,String> params);
    }
    //m层的方法
    interface ICartModel{
        void cart(HashMap<String,String> params, RequestCallBack requestCallBack);
    }
    //v层的方法
    interface ICartView{
        void onFailUre(String msg);
        void onSuccess(String result);
    }
}
