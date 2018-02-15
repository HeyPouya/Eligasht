package ir.eligasht.eligashttest.features.basket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import ir.eligasht.eligashttest.POJOs.GoodListDatabaseModel;
import ir.eligasht.eligashttest.R;
import ir.eligasht.eligashttest.features.details.DetailsActivity_;
import ir.eligasht.eligashttest.views.MyTextView;

/**
 * Created by pouya on 2/15/18.
 */

public class BasketListAdapter extends RecyclerView.Adapter<BasketListAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<GoodListDatabaseModel> list;
    private LayoutInflater inflater;
    private onClick onClick;

    public BasketListAdapter(Context mContext, List<GoodListDatabaseModel> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.basket_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.price.setText(String.format("%s %s", NumberFormat.getNumberInstance(Locale.US).format(list.get(position).getPrice()), mContext.getString(R.string.toman)));
        holder.productQty.setText(String.valueOf(list.get(position).getBuyQuantity()));
        holder.img.setImageResource(list.get(position).getPicAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClick(ir.eligasht.eligashttest.features.basket.onClick onClick) {
        this.onClick = onClick;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        MyTextView name;
        MyTextView price;
        MyTextView productQty;
        MyTextView remove;
        ImageView img;
        ImageView minus;
        ImageView plus;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            productQty = itemView.findViewById(R.id.productQty);
            img = itemView.findViewById(R.id.img);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            remove = itemView.findViewById(R.id.remove);

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(v, getAdapterPosition());
                }
            });
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(v, getAdapterPosition());
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(v, getAdapterPosition());
                }
            });

        }
    }
}
