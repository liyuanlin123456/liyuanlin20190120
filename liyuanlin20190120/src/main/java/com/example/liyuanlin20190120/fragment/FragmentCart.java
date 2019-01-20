package com.example.liyuanlin20190120.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.liyuanlin20190120.R;
import com.example.liyuanlin20190120.adapter.CartAdapter;
import com.example.liyuanlin20190120.contract.CartContract;
import com.example.liyuanlin20190120.entity.Cart;
import com.example.liyuanlin20190120.net.CartUiCallBack;
import com.example.liyuanlin20190120.presenter.CartPresenter;
import com.example.liyuanlin20190120.utils.OkhttpUtils;
import com.google.gson.Gson;

import java.util.List;

public class FragmentCart extends Fragment implements CartContract.ICartView,CartUiCallBack {

    private Button qx;
    private RecyclerView rec;
    private CheckBox checkBox;
    private TextView price;
    private Button btn;
    private CartPresenter presenter;
    private CartAdapter myAdapter;
    private List<Cart.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获取页面控件
        qx = view.findViewById(R.id.qx);
        rec = view.findViewById(R.id.rec);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkBox = view.findViewById(R.id.checkBox);
        price = view.findViewById(R.id.price);
        btn = view.findViewById(R.id.btn);
        presenter = new CartPresenter(this);
        presenter.cart(null);
        myAdapter = new CartAdapter(getActivity(),this);
        rec.setAdapter(myAdapter);
        //设置checkbox的监听
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断他的选中转态 为集合里面的参数赋值
                if (isChecked){
                    for (Cart.DataBean datum : data) {
                        datum.isChecked=true;
                        for (Cart.DataBean.ListBean listBean : datum.list) {
                            listBean.isProductChecked=true;
                        }
                    }
                }else{
                    for (Cart.DataBean datum : data) {
                        datum.isChecked=false;
                        for (Cart.DataBean.ListBean listBean : datum.list) {
                            listBean.isProductChecked=false;
                        }
                    }
                }
                myAdapter.notifyDataSetChanged();
                getPrice();
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.get(0).isChecked){
                    for (Cart.DataBean datum : data) {
                        datum.isChecked=true;
                        for (Cart.DataBean.ListBean listBean : datum.list) {
                            listBean.isProductChecked=true;
                        }
                    }
                }else{
                    for (Cart.DataBean datum : data) {
                        datum.isChecked=false;
                        for (Cart.DataBean.ListBean listBean : datum.list) {
                            listBean.isProductChecked=false;
                        }
                    }
                }
                myAdapter.notifyDataSetChanged();
                getPrice();
            }
        });
    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccess(String result) {
        Cart cart = new Gson().fromJson(result, Cart.class);
        data = cart.data;
        myAdapter.setList(cart.data);
    }
    public void getPrice(){
        //计算价钱
        double pricee=0;
        for (Cart.DataBean datum : data) {
            for (Cart.DataBean.ListBean listBean : datum.list) {
                if (listBean.isProductChecked){
                    pricee+=listBean.productNum*listBean.price;
                }
            }
        }
        price.setText("￥"+pricee);
    }

    @Override
    public void notifyPrice() {
        getPrice();
    }

    //防止内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        OkhttpUtils.getmInstance().okHttpCancel();
        presenter.onDestroy();
    }
}
