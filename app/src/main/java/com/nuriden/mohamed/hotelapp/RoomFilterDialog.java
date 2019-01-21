package com.nuriden.mohamed.hotelapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

public class RoomFilterDialog extends DialogFragment {


    DatePickerDialog picker;
    Calendar c;

    public static RoomFilterDialog newInstance(String title) {
        RoomFilterDialog frag = new RoomFilterDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.room_filter_dialog, container, false);
        // Do all the stuff to initialize your custom view

        final TextView chech_in=view.findViewById(R.id.check_in_date);
        final TextView chech_out=view.findViewById(R.id.check_out_date);
        c = Calendar.getInstance();
        view.findViewById(R.id.check_in_date_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        chech_in.setText(i+":"+(i1+1)+":"+i2);
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

                picker.show();
            }
        });


        view.findViewById(R.id.check_out_date_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        chech_out.setText(i+":"+(i1+1)+":"+i2);
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

                picker.show();
            }
        });

        return view;
    }
}
