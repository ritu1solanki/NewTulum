package com.example.tulupcoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tulupcoffee.ui.login.LoginActivity;
import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {
    //public Button mlogout;
    private FirebaseAuth mAuth;

    public CallbackManager mCallbackManager;

    private Button logout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);








        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        CardView bt=(CardView) findViewById(R.id.bt);
        CardView ct=(CardView) findViewById(R.id.ct);
        CardView bg=(CardView) findViewById(R.id.bg);
       // CardView cd=(CardView) findViewById(R.id.cd);
        CardView cc=(CardView) findViewById(R.id.cc);
        CardView cr=(CardView) findViewById(R.id.cr);
        CardView rc=(CardView) findViewById(R.id.rc);
        ImageButton logout = findViewById(R.id.logout);
       Button logoutbtn =findViewById(R.id.logoutbtn);
        mAuth = FirebaseAuth.getInstance();

        mCallbackManager = CallbackManager.Factory.create();

      logoutbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          mAuth.signOut();
              Toast.makeText(getApplicationContext(),"Logged OUT",Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(Menu.this, LoginActivity.class);
              startActivity(intent);
              finish();
              FirebaseAuth.getInstance().signOut();
             // updateUI();



          }
      });

      ct.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {



          }
      });

      bg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(Menu.this,products.class));
              finish();
          }
      });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Menu.this, LoginActivity.class));
                finish();
            }
        });

        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,BrewingMethod.class));
                finish();
            }
        });
    }

   /* private void updateUI() {
        Toast.makeText(Menu.this,"You are logged out",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Menu.this, LoginActivity.class);
        startActivity(intent);
        finish();


    }*/



    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser= mAuth.getCurrentUser();
        if(currentUser==null)
        {
           // updateUI();
        }


    }

}