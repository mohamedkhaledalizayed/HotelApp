package com.nuriden.mohamed.hotelapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    List<Reviews> reviewsList = new ArrayList<>();
    List<Recommended> recommendedList = new ArrayList<>();

    ImageView imageView;
    TextView descreption,price;
    ReviewAdapter mAdapter;
    RecyclerView recyclerView;
    int RoomPrice;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        number = getIntent().getIntExtra("number",1);
        imageView = findViewById(R.id.photo);
        price=findViewById(R.id.price);
        descreption = findViewById(R.id.description);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Reviews");
        DatabaseReference myRef2 = database.getReference("Recommended");


        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                recommendedList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Recommended university = postSnapshot.getValue(Recommended.class);
                    recommendedList.add(university);

                    // here you can access to name property like university.name

                }

                addData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                reviewsList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Reviews reviews = postSnapshot.getValue(Reviews.class);
                    reviewsList.add(reviews);

                    // here you can access to name property like university.name

                }

                loadData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private void addData() {
        descreption.setText(recommendedList.get(number-1).description);
        price.setText("$"+recommendedList.get(number-1).price+"/day");
        Picasso.get().load(recommendedList.get(number-1).image).into(imageView);

        RoomPrice = Integer.parseInt(recommendedList.get(number-1).price);
    }

    public void loadData() {
        recyclerView=findViewById(R.id.reviews_recycler);
        mAdapter = new ReviewAdapter(this, reviewsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void reservation(View view) {
        FragmentManager fm = getSupportFragmentManager();
        RoomFilterDialog editNameDialogFragment = RoomFilterDialog.newInstance(RoomPrice,number);
        editNameDialogFragment.show(fm, "Filter");
        editNameDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
