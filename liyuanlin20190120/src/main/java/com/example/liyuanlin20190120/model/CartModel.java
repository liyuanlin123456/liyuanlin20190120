package com.example.liyuanlin20190120.model;

import android.text.TextUtils;

import com.example.liyuanlin20190120.api.CartApi;
import com.example.liyuanlin20190120.contract.CartContract;
import com.example.liyuanlin20190120.net.OkhttpCallBack;
import com.example.liyuanlin20190120.net.RequestCallBack;
import com.example.liyuanlin20190120.utils.OkhttpUtils;

import java.util.HashMap;

public class CartModel implements CartContract.ICartModel {
    @Override
    public void cart(HashMap<String, String> params, final RequestCallBack requestCallBack) {
        //调用okhttp方法
        OkhttpUtils.getmInstance().doPost(CartApi.CART_SHOW, params, new OkhttpCallBack() {
            @Override
            public void onFailUre(String msg) {
                if (requestCallBack!=null){
                    requestCallBack.onFailUre("网络异常，请稍后再试");
                }
            }

            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    requestCallBack.onSuccess(result);
                }
            }
        });
    }
}
