package com.info.IsTakip;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.info.IsTakip.R;

import static android.content.SharedPreferences.*;

public class CameraActivity extends AppCompatActivity  {
    private static final int REQUEST_IMAGE_CAPTURE=101;
    public ImageView imageView;
    public Button buttonCamera,buttonBitti;
    private  Editor e;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView=findViewById(R.id.imageView1);
        buttonCamera=findViewById(R.id.buttonCamera);
        buttonBitti=findViewById(R.id.buttonBitti);
        buttonBitti.setText("Geri");
        buttonBitti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
//dd
            }
        });
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//tekrar :D
        SharedPreferences.Editor e=sp.edit();
        e.putBoolean("fotoVar",false);
        e.apply();

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageTakeIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (imageTakeIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Editor e=sp.edit();
            e.putBoolean("fotoVar",true);
            e.apply();
            Bundle extras=data.getExtras();
            Bitmap imageBitmap=(Bitmap) extras.get("data");
            System.out.println("imageBitmap    :" + imageBitmap.toString());
            imageView.setImageBitmap(imageBitmap);
            super.onBackPressed();
        }
    }

}