package com.nuriden.mohamed.hotelapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    private FirebaseAuth mAuth;

    EditText useremail,usermobile,password,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        useremail = findViewById(R.id.sign_up_user);
        usermobile = findViewById(R.id.sign_up_number);
        password = findViewById(R.id.sign_up_password);
        confirmpassword = findViewById(R.id.sign_up_confirm_password);

    }

    public void home(View view) {
        if (password.getText().toString().equals(confirmpassword.getText().toString())){
            if (useremail.getText().equals("")){
                Toast.makeText(this,"Enter User Email",Toast.LENGTH_LONG).show();
            }else {
                signUp();
            }
        }else {
            Toast.makeText(this,"Check Your Password",Toast.LENGTH_LONG).show();
        }
    }

    private void signUp() {

        mAuth.createUserWithEmailAndPassword(useremail.getText()+"", password.getText()+"")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "createUserWithEmail:success");
                            Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void signIn(View view) {
        finish();
    }
}
