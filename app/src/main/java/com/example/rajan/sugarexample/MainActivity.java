package com.example.rajan.sugarexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rajan.sugarexample.Database.Item;
import com.example.rajan.sugarexample.adapter.CustomAdapter;
import com.example.rajan.sugarexample.utils.Constants;
import com.orm.SugarContext;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Item> itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemArrayList = new ArrayList<Item>();
        SugarContext.init(this);
        intialiton();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        CustomAdapter adapter =new CustomAdapter(this,itemArrayList);
        mRecyclerView.setAdapter(adapter);

    }

    void intialiton(){

        int i = 1;
        itemArrayList.add(new Item(i++, "potato", 125));
        itemArrayList.add(new Item(i++, "banana", 90));
        itemArrayList.add(new Item(i++, "tomato", 142));
        itemArrayList.add(new Item(i++, "cold drik", 50));
        itemArrayList.add(new Item(i++, "milk", 100));
        itemArrayList.add(new Item(i++, "burgar", 200));

        SharedPreferences settings = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        if (settings.getBoolean(Constants.FIRST_TIME_PREF, true)) {
            Item.deleteAll(Item.class);
            for (Item items : itemArrayList) {
                items.save();
                Log.d("DEBUG_DATA", items.itemid + " - " + items.title + ":" + items.price);
            }
            settings.edit().putBoolean(Constants.FIRST_TIME_PREF, false).commit();


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cart) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
