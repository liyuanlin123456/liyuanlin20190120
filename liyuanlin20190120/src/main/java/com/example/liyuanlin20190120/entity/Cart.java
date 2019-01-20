package com.example.liyuanlin20190120.entity;

import java.util.List;

public class Cart {
    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean{
        public boolean isChecked;

        public List<ListBean> list;
        public String sellerName;
        public String sellerid;

        public static class ListBean{
            public boolean isProductChecked;

            public String subhead;
            public String images;
            public double price;
            public String title;

            public int productNum=1;
            public int pos;
        }
    }
}
