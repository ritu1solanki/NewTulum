package com.example.tulupcoffee;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class products extends AppCompatActivity {


   // public MaterialToolbar toolbar;

   public TabLayout tabLayout;
    public ViewPager viewPager;
    public ImageView back;

    public TabItem  coffeefrag;
    public TabItem  equipfrag;
    ArrayList<blog> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReferenceFromUrl("https://tulum-coffee.firebaseio.com/");
    ProductAdapter productAdapter;
    RecyclerView recyclerView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       /* cvtho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(products.this, Thogari.class);
                startActivity(intent);
                finish();
            }
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
       // toolbar = findViewById(R.id.mytoolbar);
      // setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        back=(ImageView) findViewById(R.id.back);
        coffeefrag=(TabItem) findViewById(R.id.coffeefrag);
        equipfrag=(TabItem) findViewById(R.id.equipmentsfrag);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(products.this,Menu.class));
                finish();
            }
        });

        tabLayout =  findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.myviewpager);
        setupviewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
//
//        ref.keepSynced(true);
//        recyclerView = viewPager.findViewById(R.id.myrecyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        list = new ArrayList<blog>();
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user!=null){
//            final DatabaseReference reference = ref.child("Users").child(user.getUid()).child("Products");
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists()){
//                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
//                            blog checkOutDetails = dataSnapshot1.getValue(blog.class);
//                            list.add(checkOutDetails);
//                        }
//                        productAdapter = new ProductAdapter(products.this, list);
//                        recyclerView.setAdapter(productAdapter);
//                    }else{
//                        Toast.makeText(products.this, "No Data Found", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }

    }
    private void setupviewpager(ViewPager viewPager) {
        viewpageadapter vpa = new viewpageadapter(getSupportFragmentManager());
        vpa.addFragment(new Coffee(), "COFFEE");
        vpa.addFragment(new Equipments(), "EQUIPMENTS");
        viewPager.setAdapter(vpa);
    }
}

