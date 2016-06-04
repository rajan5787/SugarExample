package com.example.rajan.sugarexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rajan.sugarexample.Database.CartItems;
import com.example.rajan.sugarexample.R;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.NamingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajan on 4/6/16.
 */
public class CartlistAdapter extends RecyclerView.Adapter<CartlistAdapter.viewholder> {

    Context context;
    ArrayList<CartItems> marraylist;

    public CartlistAdapter(Context context, ArrayList<CartItems> marraylist) {
        this.context = context;
        this.marraylist = marraylist;
    }

    @Override
    public CartlistAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_row,parent,false);
        return new CartlistAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(final CartlistAdapter.viewholder holder, final int position) {

        final CartItems  item = marraylist.get(position);

        holder.title.setText(item.title);
        holder.price.setText(item.price+"");
        holder.quantity.setText(item.quantity+"");

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.quantity++;
                item.save();
                holder.quantity.setText(item.quantity+"");
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.quantity > 0) {
                    item.quantity -= 1;
                    item.save();
                    holder.quantity.setText(item.quantity + "");
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartItems> item = Select.from(CartItems.class).
                                        where(Condition.prop(NamingHelper.toSQLNameDefault("itemid")).eq(marraylist.get(position).itemid+"")).
                                        list();

                item.get(0).delete();
                marraylist.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return marraylist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView title;
        TextView price;
        EditText quantity;
        Button plus;
        Button minus;
        Button delete;

        public viewholder(View itemView) {
            super(itemView);


            title = (TextView)itemView.findViewById(R.id.title);
            price = (TextView)itemView.findViewById(R.id.price);
            quantity = (EditText)itemView.findViewById(R.id.cart_item_quantity);
            plus = (Button)itemView.findViewById(R.id.cart_item_plus);
            minus = (Button)itemView.findViewById(R.id.cart_item_minus);
            delete = (Button)itemView.findViewById(R.id.cart_item_delete);
        }
    }
}
