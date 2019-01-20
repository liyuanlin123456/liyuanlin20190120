package com.example.liyuanlin20190120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.liyuanlin20190120.R;
import com.example.liyuanlin20190120.entity.Cart;
import com.example.liyuanlin20190120.net.CartCallBack;
import com.example.liyuanlin20190120.net.CartUiCallBack;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> implements CartCallBack {
    //定义上下文、集合
    private Context context;
    private List<Cart.DataBean> list;
    private CartUiCallBack cartUiCallBack;

    //有参构造
    public CartAdapter(Context context,CartUiCallBack cartUiCallBack) {
        this.context = context;
        this.list=new ArrayList<>();
        this.cartUiCallBack=cartUiCallBack;
    }

    //设置ser方法  传参
    public void setList(List<Cart.DataBean> list) {
        if (list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_cart_item, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //设置值
        viewHolder.checkBox.setChecked(list.get(i).isChecked);
        for (Cart.DataBean.ListBean listBean : list.get(i).list) {
            listBean.pos=i;
        }
        viewHolder.name.setText(list.get(i).sellerName);
        viewHolder.rec.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.rec.setAdapter(new ShoppingAdapter(context,list.get(i).list,this));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isChecked=viewHolder.checkBox.isChecked();
                for (Cart.DataBean.ListBean listBean : list.get(i).list) {
                    listBean.isProductChecked=list.get(i).isChecked;
                }
                notifyDataSetChanged();
                if (cartUiCallBack!=null){
                    cartUiCallBack.notifyPrice();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void notifyChecked(boolean isChecked, int position) {
        list.get(position).isChecked=isChecked;
        if (cartUiCallBack!=null){
            cartUiCallBack.notifyPrice();
        }
        notifyDataSetChanged();
    }

    @Override
    public void notifyNum() {
        if (cartUiCallBack!=null){
            cartUiCallBack.notifyPrice();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkBox;
        private final TextView name;
        private final RecyclerView rec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取页面的控件
            checkBox = itemView.findViewById(R.id.checkBox);
            name = itemView.findViewById(R.id.name);
            rec = itemView.findViewById(R.id.rec);
        }
    }
}
