package com.nuriden.mohamed.hotelapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MyViewHolder> {

    private List<Recommended> recommendedList;
    private Context context;
    private IRoomHandler handler;
    public RecommendedAdapter(Context context, List<Recommended> recentList) {
        this.recommendedList = recentList;
        this.context=context;
        handler=(IRoomHandler)context;
    }

    @Override
    public RecommendedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_item, parent, false);

        return new RecommendedAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecommendedAdapter.MyViewHolder holder, final int position) {
        Recommended item=recommendedList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.onClick(position+1);
            }
        });

        holder.price.setText(item.price+" $");
        Picasso.get().load(item.image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price;
        public ImageView imageView;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            price=view.findViewById(R.id.price);
            imageView=view.findViewById(R.id.image);
            cardView=view.findViewById(R.id.item_view);
        }
    }

}
