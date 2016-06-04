package com.example.rajan.sugarexample.Database;

import com.orm.SugarRecord;

/**
 * Created by rajan on 4/6/16.
 */
public class Item extends SugarRecord {
    public String title;
    public int itemid,price;
    public Item(int itemid,String title, int price) {
        this.itemid = itemid;
        this.title = title;
        this.price = price;
    }
    public Item(){

    }
}
