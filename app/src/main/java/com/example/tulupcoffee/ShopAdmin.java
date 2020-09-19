package com.example.tulupcoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_admin);
        Button prod=(Button) findViewById(R.id.prod);
        Button disc=(Button)findViewById(R.id.disc);
        ImageView back=(ImageView)findViewById(R.id.back);


        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopAdmin.this,MainSellerActivity.class));
                finish();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopAdmin.this, SellerMenu.class));
                finish();

            }
        });


    }
}