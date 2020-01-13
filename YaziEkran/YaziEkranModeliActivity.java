package com.info.IsTakip.YaziEkran;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.info.IsTakip.BuyukEkran.BuyukEkranModeli;
import com.info.IsTakip.BuyukEkran.buyuk_ekran_modelieskiActivity;
import com.info.IsTakip.CardEkran.EskiGorevler;
import com.info.IsTakip.Gorevler;
import com.info.IsTakip.CardEkran.MainActivity;

import com.info.IsTakip.R;

import java.util.ArrayList;

public class YaziEkranModeliActivity extends AppCompatActivity {
    RecyclerView rv;
    private YaziEkranModeliAdapter adapter;
    private ArrayList<Gorevler> gorevArrayList;
    private Button buttonBitenEkranYazi;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yazi_ekran_modeli);
        toolbar=findViewById(R.id.toolbar4);
        toolbar.setLogo(R.drawable.resim1);
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        toolbar.setTitle("IsTakip");
        setSupportActionBar(toolbar);
//dd
        buttonBitenEkranYazi=findViewById(R.id.buttonBitenEkranYazi);
        rv=findViewById(R.id.rv1);
        buttonBitenEkranYazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), YaziEkranModeliBitenActivity.class);
                startActivity(intent);
            }
        });

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Gorevler g1=new Gorevler(1,"1.BASLIK","1.YAPİLAN İŞ","23.08.2017","ACİL");
        Gorevler g2=new Gorevler(2,"2.BASLIK","2.YAPİLAN İŞ","23.08.2018","NORMAL");
        Gorevler g3=new Gorevler(3,"3.BASLIK","3.YAPİLAN İŞ","23.08.2019","ÇOK ACİL");
        Gorevler g4=new Gorevler(4,"4.BASLIK","4.YAPİLAN İŞ","23.08.2010","NORMAL");
        Gorevler g5=new Gorevler(5,"5.BASLIK","5.YAPİLAN İŞ","23.08.2011","ACİL");
        Gorevler g6=new Gorevler(6,"6.BASLIK","6.YAPİLAN İŞ","23.08.2013","NORMAL");
        Gorevler g7=new Gorevler(7,"7.BASLIK","7.YAPİLAN İŞ","23.08.2015","ACİL");
        Gorevler g8=new Gorevler(8,"8.BASLIK","8.YAPİLAN İŞ","23.08.2014","NORMAL");
        gorevArrayList=new ArrayList<>();
        gorevArrayList.add(g1);
        gorevArrayList.add(g2);
        gorevArrayList.add(g3);
        gorevArrayList.add(g4);
        gorevArrayList.add(g5);
        gorevArrayList.add(g6);
        gorevArrayList.add(g7);
        gorevArrayList.add(g8);

        adapter=new YaziEkranModeliAdapter(this,gorevArrayList);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_BuyukEkran:
                Intent intent1=new Intent(getApplicationContext(),YaziEkranModeliActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_YaziEkran:
                Intent intent=new Intent(getApplicationContext(), BuyukEkranModeli.class);
                startActivity(intent);
                return true;
            case R.id.action_CardEkran:
                Intent intent2=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_CardBitirilen:
                Intent intent3=new Intent(getApplicationContext(), EskiGorevler.class);
                startActivity(intent3);
                return true;
            case R.id.action_YaziEkranBitirilen:
                Intent intent4=new Intent(getApplicationContext(),YaziEkranModeliBitenActivity.class);
                startActivity(intent4);
                return true;
            case R.id.action_BuyukEkranBitirilen:
                Intent intent5=new Intent(getApplicationContext(), buyuk_ekran_modelieskiActivity.class);
                startActivity(intent5);
                return true;
            case R.id.action_cikis:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
}}
