package com.nuriden.mohamed.hotelapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;




public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<Reviews> reviewsList;
    private Context context;
    //    private IMessageHandler handler;
    public ReviewAdapter(Context context, List<Reviews> recentList) {
        this.reviewsList = recentList;
        this.context=context;
//        handler=(IMessageHandler)context;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReviewAdapter.MyViewHolder holder, final int position) {
        Reviews item= reviewsList.get(position);

        holder.review.setText(item.review);
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView review;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            review=view.findViewById(R.id.review);
            imageView=view.findViewById(R.id.user);
        }
    }

}
