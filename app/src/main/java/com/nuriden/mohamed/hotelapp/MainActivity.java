package com.nuriden.mohamed.hotelapp;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.admin){
            Intent intent=new Intent(this,AdminActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.aboutapp){
            Intent intent=new Intent(this,AboutActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.logout){
            mAuth.signOut();
            Intent intent=new Intent(this,SignInActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAll(View view) {
        startActivity(new Intent(this,FilterResultActivity.class));
    }

    public void showRoomNubmer1(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",1);
        startActivity(intent);
    }

    public void showRoomNubmer2(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",2);
        startActivity(intent);
    }

    public void showRoomNubmer3(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",3);
        startActivity(intent);
    }

    public void showRoomNubmer4(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",4);
        startActivity(intent);
    }

    public void showRoomNubmer5(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",5);
        startActivity(intent);
    }

    public void showRoomNubmer6(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",6);
        startActivity(intent);
    }

    public void showRoomNubmer7(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",7);
        startActivity(intent);
    }

    public void showRoomDiscount1(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",1);
        startActivity(intent);
    }

    public void showRoomDiscount2(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",2);
        startActivity(intent);
    }

    public void showRoomDiscount3(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",9);
        startActivity(intent);
    }

    public void showRoomDiscount4(View view) {
        Intent intent=new Intent(this,RoomDetailsActivity.class);
        intent.putExtra("number",10);
        startActivity(intent);
    }
}
