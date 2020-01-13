package com.info.IsTakip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.info.IsTakip.R;

public class TasarimPersonelActivity extends AppCompatActivity {
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    private Button buttonFotoCek,buttonIslemTamamla;
    private TextView textViewAciklama;
    private ImageView imageViewPersonel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasarim_personel);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        buttonFotoCek=findViewById(R.id.buttonFotoCek);
        buttonIslemTamamla=findViewById(R.id.buttonIsYap);
        textViewAciklama=findViewById(R.id.textViewAciklama);
//gg
        imageViewPersonel=findViewById(R.id.imageViewPersonel);
        buttonIslemTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),tasarim_ozetActivity.class);
                startActivity(intent);
            }
        });

    }
}
