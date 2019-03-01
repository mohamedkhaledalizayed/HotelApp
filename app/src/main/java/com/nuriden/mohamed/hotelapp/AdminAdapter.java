package com.nuriden.mohamed.hotelapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {

    private List<Reservation> reservationList;
    private Context context;
//    private IRoomHandler handler;
    public AdminAdapter(Context context, List<Reservation> recentList) {
        this.reservationList = recentList;
        this.context=context;
//        handler=(IRoomHandler)context;
    }

    @Override
    public AdminAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_view_item, parent, false);

        return new AdminAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdminAdapter.MyViewHolder holder, final int position) {
        Reservation item=reservationList.get(position);

        holder.room_number.setText("Room Number : "+item.RoomNumber);
        holder.price.setText("Price : $"+item.Price);
        holder.check_in.setText("Check In : "+item.CheckIn);
        holder.check_out.setText("Check Out : "+item.CheckOut);
        holder.number_days.setText("Number Of Days : "+item.NumberOfDays);
        holder.adult_number.setText("Adult Number : "+item.AdultNumber);
        holder.children_number.setText("Children Number : "+item.ChildrenNumber);
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView room_number,price,check_in,check_out,number_days,adult_number,children_number;
        public MyViewHolder(View view) {
            super(view);

            room_number=view.findViewById(R.id.room_number);
            price=view.findViewById(R.id.price);
            check_in=view.findViewById(R.id.check_in);
            check_out=view.findViewById(R.id.check_out);
            number_days=view.findViewById(R.id.number_days);
            adult_number=view.findViewById(R.id.adult_number);
            children_number=view.findViewById(R.id.children_number);

        }
    }

}
