package com.example.tulupcoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SellerMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_menu);
        CardView shop=(CardView) findViewById(R.id.bt);
        CardView brewguide=(CardView) findViewById(R.id.ct);
        CardView dict=(CardView) findViewById(R.id.dict);
        Button back=(Button) findViewById(R.id.back);
        CardView rowcards=(CardView) findViewById(R.id.rowcards);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerMenu.this,ShopAdmin.class));
                finish();
            }
        });
        brewguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerMenu.this,BrewGuides.class));
                finish();

            }
        });

        dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerMenu.this,Dict.class));
                finish();

            }
        });

        rowcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerMenu.this,Rowcardsadmin.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerMenu.this,SellerLogin.class));
                finish();

            }
        });


    }
}