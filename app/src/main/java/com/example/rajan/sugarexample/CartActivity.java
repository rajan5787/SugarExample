package com.example.rajan.sugarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.rajan.sugarexample.Database.CartItems;
import com.example.rajan.sugarexample.adapter.CartlistAdapter;
import com.example.rajan.sugarexample.adapter.CustomAdapter;
import com.orm.SugarContext;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    CartlistAdapter adapter;
    ArrayList<CartItems> itemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_cart);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
         adapter =new CartlistAdapter(this,itemArrayList);
        mRecyclerView.setAdapter(adapter);
    }

    private void getData() {
        itemArrayList = new ArrayList<>(CartItems.listAll(CartItems.class));
    }

}
