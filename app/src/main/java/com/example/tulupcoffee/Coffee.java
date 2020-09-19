package com.example.tulupcoffee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Coffee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Coffee extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<blog> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReferenceFromUrl("https://tulum-coffee.firebaseio.com/");
    ProductAdapter productAdapter;
    RecyclerView recyclerView;

    View ContentView;


//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Coffee.
//     */
//    // TODO: Rename and change types and number of parameters
    public static Coffee newInstance(String param1, String param2) {
        Coffee fragment = new Coffee();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Coffee() {    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ContentView =  inflater.inflate(R.layout.fragment_coffee, container, false);
        recyclerView = ContentView.findViewById(R.id.myrecyclerview);
        ref.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<blog>();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            final DatabaseReference reference = ref.child("Users").child(user.getUid()).child("Products");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                            blog checkOutDetails = dataSnapshot1.getValue(blog.class);
                            list.add(checkOutDetails);
                        }
                        productAdapter = new ProductAdapter(getContext(), list);
                        recyclerView.setAdapter(productAdapter);
                    }else{
                        Toast.makeText(getContext(), "Your Key is wrong "+user.getUid(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getContext(), "Error "+error, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return ContentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//

    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_coffee, container,    false);
//
//        mdatabase= FirebaseDatabase.getInstance().getReference().child("Products");
//        mdatabase.keepSynced(true);
//
//
//
//
//
//        mbloglist = rootView.findViewById(R.id.myrecyclerview);
//        mbloglist.setHasFixedSize(true);
//        /*mbloglist.setLayoutManager(new LinearLayoutManager(t));
//*/
//        return rootView;
//
//       // return inflater.inflate(R.layout.fragment_coffee, container, false);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<blog,blogviewholder>firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<blog, blogviewholder>(blog.class,R.layout.cardviewproducts,blogviewholder.class,mdatabase) {
//            @Override
//            protected void populateViewHolder(blogviewholder blogviewholder, blog blog, int i) {
//                blogviewholder.setProducttitle(blog.getProducttitle());
//                blogviewholder.setDiscountprice(blog.getDiscountprice());
//                blogviewholder.setProductcategory(blog.getProductcategory());
//                blogviewholder.setProductdescription(blog.getProductdescription());
//                blogviewholder.setProductqty(blog.getProductqty());
//                blogviewholder.setProducticon(getContext(),blog.getProducticon());
//
//
//            }
//        };
//        mbloglist.setAdapter(firebaseRecyclerAdapter);
//
//    }
//    public static class blogviewholder extends RecyclerView.ViewHolder{
//        View mView;
//        public blogviewholder(@NonNull View itemView) {
//            super(itemView);
//            mView=itemView;
//        }
//        public void setProductqty(String Productqty){
//            TextView post_Productqty=(TextView) mView.findViewById(R.id.qtyet);
//            post_Productqty.setText(Productqty);
//        }
//
//        public void setDiscountprice(String discountprice) {
//            TextView post_discountprice=(TextView) mView.findViewById(R.id.discountet);
//            post_discountprice.setText(discountprice);
//
//        }
//        public void setOriginalprice(String originalprice) {
//            TextView post_originalprice=(TextView) mView.findViewById(R.id.priceet);
//            post_originalprice.setText(originalprice);
//
//
//        }
//
//        public void setProductcategory(String productcategory) {
//
//            TextView post_productcategory=(TextView) mView.findViewById(R.id.categoryet);
//            post_productcategory.setText(productcategory);
//
//
//
//        }
//
//        public void setProductdescription(String productdescription) {
//            TextView post_productdescription=(TextView) mView.findViewById(R.id.descripet);
//            post_productdescription.setText(productdescription);
//
//        }
//
//        public void setProducticon(Context ctx, String producticon) {
//            ImageView post_image=(ImageView) mView.findViewById(R.id.productimage);
//            Picasso.get().load(producticon).into(post_image);
//        }
//        public void setProducttitle(String producttitle) {
//            TextView post_producttitle=(TextView) mView.findViewById(R.id.titleet);
//            post_producttitle.setText(producttitle);
//
//
//        }
}