package com.nuriden.mohamed.hotelapp;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.filter){
            FragmentManager fm = getSupportFragmentManager();
            RoomFilterDialog editNameDialogFragment = RoomFilterDialog.newInstance("");
            editNameDialogFragment.show(fm, "Filter");
            editNameDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        }

        return super.onOptionsItemSelected(item);
    }

    public void showRoom(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        startActivity(intent);
    }

    public void showAll(View view) {
        startActivity(new Intent(this,FilterResultActivity.class));
    }
}
