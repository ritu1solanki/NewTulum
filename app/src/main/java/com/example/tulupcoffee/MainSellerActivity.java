package com.example.tulupcoffee;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class MainSellerActivity extends AppCompatActivity  {


    public static final int CAMERA_REQUEST_CODE=200;
    public static final int STORAGE_REQUEST_CODE=300;

    public static final int IMAGE_PICK_GALLERY_CODE=400;
    public static final int IMAGE_PICK_CAMERA_CODE=500;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri image_uri;


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;




   public ImageView back,productimage;
   public EditText title,description,category,qty,price,discount;
   public Button addproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);
        final ImageButton logout= findViewById(R.id.logout);
        final ImageView back=findViewById(R.id.back);
        productimage=findViewById(R.id.productimage);
        title=findViewById(R.id.titleet);
        description=(EditText) findViewById(R.id.descripet);
        category=findViewById(R.id.categoryet) ;
        //qty=findViewById(R.id.qtyet);
        price=findViewById(R.id.priceet);
        discount=findViewById(R.id.discountet);
        addproduct=findViewById(R.id.addproduct);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("Please");
        progressDialog.setCanceledOnTouchOutside(false);

        ActionBar actionbar= getSupportActionBar();
        actionbar.hide();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainSellerActivity.this, ShopAdmin.class));
                finish();

            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainSellerActivity.this, SellerLogin.class));
                finish();
            }
        });

        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showimagepickdialog();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorydialog();
            }
        });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();
            }
        });






    }

     private String producttitle,productdescription,productcategory,Productqty,originalprice,discountprice;
    private boolean discountavailavle=false;

    private void inputdata() {

        producttitle=title.getText().toString().trim();
        productdescription=description.getText().toString().trim();
        productcategory=category.getText().toString().trim();
       // Productqty=qty.getText().toString().trim();
        originalprice=price.getText().toString().trim();
        discountprice=discount.getText().toString().trim();

        if(TextUtils.isEmpty(producttitle))
        {
            Toast.makeText(this,"Title is required",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(productdescription))
        {
            Toast.makeText(this,"Description is required",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(productcategory))
        {
            Toast.makeText(this,"Category is required",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(originalprice))
        {
            Toast.makeText(this,"originalprice is required",Toast.LENGTH_SHORT).show();
        }


       /* if(TextUtils.isEmpty(Productqty))
        {
            Toast.makeText(this,"Quantity price is required",Toast.LENGTH_SHORT).show();
        }*/
        if(discountavailavle)
        {
            discountprice=discount.getText().toString().trim();

            if(TextUtils.isEmpty(discountprice))
            {
                Toast.makeText(this,"Discount price is required",Toast.LENGTH_SHORT).show();
                return;
            }

        }
        else{
            discountprice="0";
        }

        addproducttodb();



    }

    private void addproducttodb() {

        progressDialog.setTitle("Adding Product");
        progressDialog.show();

        final String timestamp=""+System.currentTimeMillis();
        if(image_uri==null)
        {
            HashMap<String, Object> userdataMap = new HashMap<>();
            userdataMap.put("uid",""+ firebaseAuth.getUid());
            userdataMap.put("productid",""+ timestamp);
            userdataMap.put("producttitle",""+ producttitle);
            userdataMap.put("productdescription",""+ productdescription);
            userdataMap.put("producticon","");
            userdataMap.put("discountavailavle",""+ discountavailavle);

            userdataMap.put("productcategory",""+ productcategory);
            userdataMap.put("originalprice",""+ originalprice);
           /* userdataMap.put("Productqty",""+ Productqty);*/
            userdataMap.put("discountprice",""+ discountprice);
            userdataMap.put("timestamp",""+ timestamp);

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
            reference.child(firebaseAuth.getUid()).child("Products").child(timestamp).setValue(userdataMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            progressDialog.dismiss();
                            Toast.makeText(MainSellerActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
                           cleardata();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MainSellerActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    });




        }
        else{

            String filepathandname= "product_images/"+""+timestamp;
            StorageReference storageReference= FirebaseStorage.getInstance().getReference(filepathandname);
            storageReference.putFile(image_uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isSuccessful());
                            Uri downloadimageuri=uriTask.getResult();
                            if(uriTask.isSuccessful())
                            {
                                HashMap<String, Object> userdataMap = new HashMap<>();
                                userdataMap.put("uid",""+ firebaseAuth.getUid());
                                userdataMap.put("productid",""+ timestamp);
                                userdataMap.put("producttitle",""+ producttitle);
                                userdataMap.put("productdescription",""+ productdescription);
                                userdataMap.put("producticon",""+downloadimageuri);
                                userdataMap.put("discountavailavle",""+ discountavailavle);

                                userdataMap.put("productcategory",""+ productcategory);
                                userdataMap.put("originalprice",""+ originalprice);
                               /* userdataMap.put("Productqty",""+ Productqty);*/
                                userdataMap.put("discountprice",""+ discountprice);
                                userdataMap.put("timestamp",""+ timestamp);

                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
                                reference.child(firebaseAuth.getUid()).child("Products").child(timestamp).setValue(userdataMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                progressDialog.dismiss();;
                                                Toast.makeText(MainSellerActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
                                               cleardata();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(MainSellerActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();


                                            }
                                        });



                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainSellerActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });

        }

    }

    private void cleardata(){
        title.setText(" ");
        description.setText(" ");
        category.setText(" ");
       /* qty.setText(" ");*/
        price.setText(" ");
        discount.setText(" ");
        productimage.setImageResource(R.drawable.ic_baseline_add_product);
        image_uri=null;
    }

    private void categorydialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Product Category")
                .setItems(Constants.productcategories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String categ= Constants.productcategories[which];
                        Log.d("home", "onFailure: failed" + categ);
                        category.setText(categ);


                    }
                }).show();
    }

    private void showimagepickdialog() {

        String options[] ={"Camera","Gallery"};
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("PickImage")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {


                        if(which==0){
                            if(checkcamerapermissions()){
                                pickfromcamera();
                            }
                            else {
                                requestcamerapermission();
                            }
                        }
                        else {
                            if(checkstoragepermission())
                            {
                                pickfromgallery();
                            }
                            else {
                                requeststoragepermission();

                            }
                        }

                    }
                })
                .show();

    }

    private void pickfromgallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE) ;
    }

    private void pickfromcamera()
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description");

        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(intent,IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkstoragepermission()
    {
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==(PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void requeststoragepermission(){
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkcamerapermissions()
    {
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) ==(PackageManager.PERMISSION_GRANTED);

        boolean result1= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==(PackageManager.PERMISSION_GRANTED);
        return result && result1;

    }

    private void requestcamerapermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
            if(grantResults.length>0){
                boolean cameraAccepted= grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean storageAccepted= grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted && storageAccepted){
                    pickfromcamera();
                }

                else {
                    Toast.makeText(this,"Camera and Storage permissions are required!",Toast.LENGTH_SHORT).show();


                }
            }

            }

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted= grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickfromgallery();
                    }
                    else {
                        Toast.makeText(this,"Storage permissions is required!",Toast.LENGTH_SHORT).show();

                    }


                }

            }

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode== RESULT_OK){
            if(requestCode== IMAGE_PICK_GALLERY_CODE)
            {
                image_uri=data.getData();

                productimage.setImageURI(image_uri);
            }
            else if(requestCode== IMAGE_PICK_CAMERA_CODE)
            {
                productimage.setImageURI(image_uri);
            }
        }




        super.onActivityResult(requestCode, resultCode, data);
    }
}