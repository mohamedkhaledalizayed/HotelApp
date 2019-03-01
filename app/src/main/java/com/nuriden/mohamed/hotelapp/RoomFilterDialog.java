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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RoomFilterDialog extends DialogFragment {


    DatePickerDialog picker;
    Calendar c;
    String chechOut,chechIn;
    private DatabaseReference mDatabase;
    EditText roomNumber,adultNumber,ChildrenNumber;
    int roomPrice;
    int number;
    public static RoomFilterDialog newInstance(int price,int number) {
        RoomFilterDialog frag = new RoomFilterDialog();
        Bundle args = new Bundle();
        args.putInt("price", price);
        args.putInt("number",number);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        roomPrice=getArguments().getInt("price",110);
        number=getArguments().getInt("number",1);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation");

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
                        chechIn=i+":"+(i1+1)+":"+i2;
                        chech_in.setText(chechIn);
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
                        chechOut=i+":"+(i1+1)+":"+i2;
                        chech_out.setText(chechOut);
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

                picker.show();
            }
        });



         roomNumber= view.findViewById(R.id.room_numnber);
         adultNumber= view.findViewById(R.id.adult_numnber);
         ChildrenNumber= view.findViewById(R.id.children_numnber);

        Button button=view.findViewById(R.id.saveData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chechIn==null || chechOut==null){
                    Toast.makeText(getActivity(),"Please choose check in and check out first",Toast.LENGTH_LONG).show();
                }else {
                    saveData();
                }
            }
        });

        return view;
    }

    private void saveData() {

        int DaysNum = Integer.parseInt(roomNumber.getText().toString());
        int ChildrenNum = Integer.parseInt(ChildrenNumber.getText().toString());

        int price = (roomPrice + (15 * ChildrenNum)) * DaysNum;
        Map<String,String> map=new HashMap<>();
        map.put("CheckIn",chechIn);
        map.put("CheckOut",chechOut);
        map.put("NumberOfDays",roomNumber.getText()+"");
        map.put("AdultNumber",adultNumber.getText()+"");
        map.put("ChildrenNumber",ChildrenNumber.getText()+"");
        map.put("RoomNumber",number+"");
        map.put("Price",price +"");

        mDatabase.push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(),"Data Saved Successfully",Toast.LENGTH_LONG).show();
            }
        });

        dismiss();
    }
}
