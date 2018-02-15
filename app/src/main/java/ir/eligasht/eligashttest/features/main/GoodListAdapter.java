package ir.eligasht.eligashttest.features.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ir.eligasht.eligashttest.POJOs.GoodListDatabaseModel;
import ir.eligasht.eligashttest.R;
import ir.eligasht.eligashttest.features.details.DetailsActivity_;
import ir.eligasht.eligashttest.tools.Consts;
import ir.eligasht.eligashttest.views.MyTextView;

/**
 * Created by pouya on 2/15/18.
 */

public class GoodListAdapter extends RecyclerView.Adapter<GoodListAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<GoodListDatabaseModel> list;
    LayoutInflater inflater;

    public GoodListAdapter(Context mContext, List<GoodListDatabaseModel> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.price.setText(String.format("%s %s", list.get(position).getPrice(), mContext.getString(R.string.toman)));
        holder.quantity.setText(String.format("%s : %s", mContext.getString(R.string.quantity),list.get(position).getQuantity()));
        holder.img.setImageResource(list.get(position).getPicAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        MyTextView name;
        MyTextView price;
        MyTextView quantity;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsActivity_.intent(mContext).id(list.get(getAdapterPosition()).getId()).start();
                }
            });
        }
    }
}
