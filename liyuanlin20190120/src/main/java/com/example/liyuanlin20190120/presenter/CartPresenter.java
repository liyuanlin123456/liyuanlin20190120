package com.example.liyuanlin20190120.presenter;

import com.example.liyuanlin20190120.contract.CartContract;
import com.example.liyuanlin20190120.model.CartModel;
import com.example.liyuanlin20190120.net.RequestCallBack;

import java.util.HashMap;

public class CartPresenter extends CartContract.CartPresenter {
    //获得m层 v层
    private CartModel cartModel;
    private CartContract.ICartView iCartView;

    public CartPresenter(CartContract.ICartView iCartView) {
        this.cartModel=new CartModel();
        this.iCartView = iCartView;
    }

    @Override
    public void cart(HashMap<String, String> params) {
        if (cartModel!=null){
            cartModel.cart(params, new RequestCallBack() {
                @Override
                public void onFailUre(String msg) {
                    if (iCartView!=null){
                        iCartView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccess(String result) {
                    if (iCartView!=null){
                        iCartView.onSuccess(result);
                    }
                }
            });
        }
    }
    public void onDestroy(){
        if (iCartView!=null){
            iCartView=null;
        }
    }
}
