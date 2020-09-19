package com.example.tulupcoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tulupcoffee.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SellerLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button vendorlogin =findViewById(R.id.vendorlogin);
        //final Button vendorsignup =findViewById(R.id.vendorsignup);
        final TextView usertv=findViewById(R.id.usertv);

        /*vendorsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SellerLogin.this, RegisterSeller.class));
                finish();
            }
        });*/




        usertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerLogin.this, LoginActivity.class));
                finish();

            }
        });


        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        vendorlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(SellerLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    String input = "Authentication Failed";
                                    input += "\n";
                                    input += "Please Enter Registered Mail ID and Password";
                                    input += "\n";
                                    input += "Or Create new account";
                                    Toast.makeText(SellerLogin.this, input, Toast.LENGTH_LONG).show();



                                } else {
                                    startActivity(new Intent(SellerLogin.this,SellerMenu.class));
                                    finish();


                                }
                            }
                        });
            }
        });







    }
}