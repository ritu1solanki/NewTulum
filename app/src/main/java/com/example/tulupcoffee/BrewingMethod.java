package com.example.tulupcoffee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BrewingMethod extends AppCompatActivity {

    LinearLayout aeropress,french,v60,moka_pota;

    ImageView back,hm;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewing_method);
        initialise();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(BrewingMethod.this, Menu.class);

                startActivity(intent);
                finish();


            }
        });
        aeropress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                Intent intent =new Intent(BrewingMethod.this,Ingrediant_Selection.class);
                intent.putExtra("Flag",flag+"");
                startActivity(intent); finish();

            }
        });
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                Intent intent =new Intent(BrewingMethod.this,Ingrediant_Selection.class);
                intent.putExtra("Flag",flag+"");
                startActivity(intent); finish();
            }
        });
        v60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 3;
                Intent intent =new Intent(BrewingMethod.this,Ingrediant_Selection.class);
                intent.putExtra("Flag",flag+"");
                startActivity(intent); finish();
            }
        });
        moka_pota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 4;
                Intent intent =new Intent(BrewingMethod.this,Ingrediant_Selection.class);
                intent.putExtra("Flag",flag+"");
                startActivity(intent); finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BrewingMethod.this,Menu.class));
                finish();
            }
        });
    }
    private void initialise() {
        aeropress = findViewById(R.id.aerospace);
        french = findViewById(R.id.french_press);
        v60 = findViewById(R.id.pourover);
        moka_pota = findViewById(R.id.moka_pot);
        back = findViewById(R.id.back);
        hm=(ImageView)findViewById(R.id.hm);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BrewingMethod.this,Menu.class));
        finish();
    }
}