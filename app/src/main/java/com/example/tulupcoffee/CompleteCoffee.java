package com.example.tulupcoffee;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class CompleteCoffee extends AppCompatActivity {

    TextView method_name;
    ImageView method_photo,back,camera;
    String flag;
    private static final int CAMERA_PIC_REQUEST = 1337;
    private static final int REQUEST_EXTERNAL_STORAGE_RESULT = 1;
    private Uri imageUri;
    //private static final String FILE_NAME = "image01.jpg";
    //File pictureDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"CameraDemo");
   // private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_coffee);
        initialise();
        if(flag.equals("1")){
            method_photo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.aeropress));
            method_name.setText("AeroPress");
        }else if(flag.equals("2")){
            method_photo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.frenchpress));
            method_name.setText("FrenchPress");
        }else if(flag.equals("3")){
            method_photo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.v60));
            method_name.setText("v60 PourOver");
        }else if(flag.equals("4")){
            method_photo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mokapot));
            method_name.setText("Moka Pot");
        }

//        if (!pictureDir.exists()) {
//            pictureDir.mkdirs();
//        }
        // gifImageView.setVisibility(View.VISIBLE);
//        GifDrawable gifDrawable = null;
//        try {
//            gifDrawable = new GifDrawable(getResources(), R.drawable.correct1);
//            gifDrawable.setLoopCount(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        gifImageView.setImageDrawable(gifDrawable);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(CompleteCoffee.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    callCameraApp();
                }
                else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CompleteCoffee.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    {
                        Toast.makeText(CompleteCoffee.this, "External storage permission" + " required to save images", Toast.LENGTH_SHORT).show();
                    }
                    ActivityCompat.requestPermissions(CompleteCoffee.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_EXTERNAL_STORAGE_RESULT);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompleteCoffee.this,Ingrediant_Selection.class);
                intent.putExtra("Flag",flag);
                startActivity(intent);
                finish();
            }
        });
    }
    public void callCameraApp()
    {
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            String url = MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "", "");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            String url = getRealPathFromURI(imageUri);
            //Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            //imgView.setImageBitmap(thumbnail);
            intent.putExtra(Intent.EXTRA_STREAM,  Uri.parse(String.valueOf(url)));
            intent.setType("*/*");
            //intent.putExtra(Intent.EXTRA_TEXT, fileUri);
            startActivity(Intent.createChooser(intent, "Share"));
            //startActivity(intent)
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You did not click the photo", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == REQUEST_EXTERNAL_STORAGE_RESULT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCameraApp();
            }
            else {
                Toast.makeText(this, "External write permission" + " has not been granted, " + " cannot saved images", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        }
    }
    private void initialise() {
        ActionBar actioBar = getSupportActionBar();
        actioBar.hide();
        Intent intent = getIntent();
        flag = intent.getStringExtra("Flag");
        method_name = findViewById(R.id.method_name);
        method_photo = findViewById(R.id.method_photo);
        back = findViewById(R.id.back);
        camera = findViewById(R.id.camera);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CompleteCoffee.this,Ingrediant_Selection.class);
        intent.putExtra("Flag",flag);
        startActivity(intent);
        finish();
    }
}