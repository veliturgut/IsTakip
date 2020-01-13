package com.info.IsTakip.GorevTasarimBilgi;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.info.IsTakip.CameraActivity;
import com.info.IsTakip.NfcokuActivity;

import com.info.IsTakip.gps;
import com.info.IsTakip.R;

import java.util.ArrayList;

public class GorevTasarimBilgiActivity extends AppCompatActivity {
    private Button buttonBitirme,buttonKaydet;
    private EditText editTextAlert, editTextAciklama;
    private TextView textViewBaslik,textViewYapilanIs,textViewTarihKimAtti,textViewKimAtti,textViewAcil,textViewTarihKimeAtti;

    private RecyclerView rv;
    private GorevTasarimBilgiAdapter adapter;
    private ArrayList<DetayGörevler> gorevArraylist;
    gps konumServisi;
    private int izinKontrol;
    private SharedPreferences sp;
    private SharedPreferences.Editor e;
//dd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorev_tasarim_bilgi);
        editTextAciklama=findViewById(R.id.editTextAciklama);
        buttonKaydet=findViewById(R.id.buttonKaydet);
        textViewTarihKimAtti=findViewById(R.id.textViewKimAtti);
        textViewBaslik=findViewById(R.id.textViewBaslikkk);
        textViewTarihKimAtti=findViewById(R.id.textViewTarihKimAtti);
        textViewKimAtti=findViewById(R.id.textViewKimAtti);
        textViewAcil=findViewById(R.id.textViewAcilll);
        textViewYapilanIs=findViewById(R.id.textViewYapilanIs);
        buttonBitirme=findViewById(R.id.buttonBitir);
        final String[] k = new String[]{"nfc","kamera","gps"};
        buttonBitirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        for (int b = 0; b < k.length; b++) {
            if (k[b] == "nfc") {
                Intent intent=new Intent(getApplicationContext(), NfcokuActivity.class);
                startActivity(intent);
            }
            if (k[b] == "kamera") {
                Intent intent=new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
            if (k[b] == "gps") {
                buttonBitirme.setVisibility(View.VISIBLE);izinKontrol= ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                if (izinKontrol!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) getApplicationContext(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
                }
                konumServisi = new gps(getApplicationContext());
                if(konumServisi.getKonumaErisim()){
                    double enlem=konumServisi.getEnlem();
                    double boylam=konumServisi.getBoylam();
                    Log.d("KONUM_SERVISI","ENLEM: "+enlem);
                    Log.d("KONUM_SERVISI","BOYLAM: "+boylam);
                }
                else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(getApplicationContext());
                    adb.setTitle("GPS ayarları");
                    adb.setMessage("Konum servisi kapalıdır. Açmak için Düzenle'ye tıklayınız.");
                    adb.setPositiveButton("Düzenle", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    adb.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    adb.show();
                }
            }
        }
            }
        });
        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Gönderildi",Toast.LENGTH_SHORT).show();
            }
        });
        rv=findViewById(R.id.rvorta);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        DetayGörevler g2=new DetayGörevler(2,"2.BASLIK","B binası temizlendi","N64D5",false,false);
        DetayGörevler g3=new DetayGörevler(3,"3.BASLIK","C binası temizlendi","A4DG4",false,false);
        DetayGörevler g4=new DetayGörevler(4,"4.BASLIK","D binası temizlendi","BSE3D",false,false);
        DetayGörevler g5=new DetayGörevler(5,"5.BASLIK","E binası temizlendi","ADF45",false,false);
        DetayGörevler g6=new DetayGörevler(6,"6.BASLIK","F binası temizlendi","SAD3S",false,false);
        DetayGörevler g7=new DetayGörevler(7,"7.BASLIK","G binası temizlendi","SD3C4",false,false);
        DetayGörevler g8=new DetayGörevler(8,"8.BASLIK","Z binası temizlendi","SDE3F4",false,false);


        gorevArraylist=new ArrayList<>();
        gorevArraylist.add(new DetayGörevler(1,"1.Başlık","A binası temizlendi","A3D4C4",false,false));
        gorevArraylist.add(g2);
        gorevArraylist.add(g3);
        gorevArraylist.add(g4);
        gorevArraylist.add(g5);
        gorevArraylist.add(g6);
        gorevArraylist.add(g7);
        gorevArraylist.add(g8);

        adapter=new GorevTasarimBilgiAdapter(this,gorevArraylist);
        rv.setAdapter(adapter);

        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e = sp.edit();
        e.putInt("cbIndex",-1);
        e.putBoolean("fotoVar", false);
        e.putBoolean("nfcVar",false);
        e.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.cbTikKontol(sp.getInt("cbIndex",-1),sp.getBoolean("fotoVar",false));
        adapter.cbTikkkKontrol(sp.getInt("cbIndex",-1),sp.getBoolean("nfcVar",false));

    }

}
