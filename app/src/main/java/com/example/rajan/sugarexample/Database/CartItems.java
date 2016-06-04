package com.example.rajan.sugarexample.Database;

import com.orm.SugarRecord;

/**
 * Created by pradeet on 9/5/16.
 */
public class CartItems extends SugarRecord {
    public int itemid;
   public String title;
    public int price;
    public int quantity;
    public CartItems() {

    }

    public CartItems(int itemid,String title,int price, int quantity) {
        this.itemid = itemid;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
}
