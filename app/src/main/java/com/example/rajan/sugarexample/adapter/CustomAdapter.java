package com.example.rajan.sugarexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajan.sugarexample.Database.CartItems;
import com.example.rajan.sugarexample.Database.Item;
import com.example.rajan.sugarexample.R;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.NamingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajan on 4/6/16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewholder> {

    ArrayList<Item> mArraylist;
    Context context;
    public CustomAdapter(Context context,ArrayList<Item> mArraylist) {
        this.context = context;
        this.mArraylist = mArraylist;
    }

    @Override
    public CustomAdapter.viewholder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detail,parent,false);
        return new CustomAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.viewholder holder, int position) {

        final Item item = mArraylist.get(position);
        holder.name.setText(item.title);
        holder.collage.setText(item.price+"");


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<CartItems> items = Select.from(CartItems.class)
                        .where(Condition.prop(NamingHelper.toSQLNameDefault("itemid")).eq(item.itemid+ ""))
                        .list();
                if(items.size()==0){
                    CartItems new_item = new CartItems(item.itemid,item.title,item.price,1);
                    new_item.save();
                    Toast.makeText(context, "Added To cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "The Item is already added to the chart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{


        TextView name,collage;
        Button button;
        public viewholder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.title);
            collage = (TextView)itemView.findViewById(R.id.price);
            button = (Button)itemView.findViewById(R.id.button);
        }
    }
}
