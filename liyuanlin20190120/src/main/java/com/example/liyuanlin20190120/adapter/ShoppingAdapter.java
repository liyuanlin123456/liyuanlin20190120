package com.example.liyuanlin20190120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liyuanlin20190120.R;
import com.example.liyuanlin20190120.entity.Cart;
import com.example.liyuanlin20190120.net.CartCallBack;
import com.example.liyuanlin20190120.view.AddMinusNum;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    //获取上下文、list集合
    private Context context;
    private List<Cart.DataBean.ListBean> list;
    private CartCallBack cartCallBack;

    //有参构造
    public ShoppingAdapter(Context context, List<Cart.DataBean.ListBean> list, CartCallBack cartCallBack) {
        this.context = context;
        this.list = list;
        this.cartCallBack = cartCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_cart_shopping_item, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //设置值
        viewHolder.jj.setText(list.get(i).subhead);
        String[] split = list.get(i).images.split("!");
        Glide.with(context).load(split[0]).into(viewHolder.image);
        viewHolder.title.setText(list.get(i).title);
        viewHolder.price.setText("￥"+list.get(i).price);
        viewHolder.checkBox.setChecked(list.get(i).isProductChecked);
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isProductChecked=viewHolder.checkBox.isChecked();
                for (Cart.DataBean.ListBean listBean : list) {
                    if (!listBean.isProductChecked){
                        cartCallBack.notifyChecked(false,listBean.pos);
                        return;
                    }else{
                        cartCallBack.notifyChecked(true,listBean.pos);
                    }
                }
            }
        });
        viewHolder.addMinusNum.setNum(list.get(i).productNum);
        viewHolder.addMinusNum.setNumCallBack(new AddMinusNum.NumCallBack() {
            @Override
            public void getNum(int num) {
                list.get(i).productNum=num;
                notifyDataSetChanged();
                if (cartCallBack!=null){
                    cartCallBack.notifyNum();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView jj;
        private final CheckBox checkBox;
        private final ImageView image;
        private final TextView title;
        private final TextView price;
        private final AddMinusNum addMinusNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取页面控件
            jj = itemView.findViewById(R.id.jj);
            checkBox = itemView.findViewById(R.id.checkBox);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            addMinusNum = itemView.findViewById(R.id.addMinusNum);
        }
    }
}
