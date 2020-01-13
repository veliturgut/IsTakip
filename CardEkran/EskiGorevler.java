package com.info.IsTakip.CardEkran;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.info.IsTakip.BuyukEkran.BuyukEkranModeli;
import com.info.IsTakip.BuyukEkran.buyuk_ekran_modelieskiActivity;
import com.info.IsTakip.Gorevler;

import com.info.IsTakip.YaziEkran.YaziEkranModeliActivity;
import com.info.IsTakip.YaziEkran.YaziEkranModeliBitenActivity;
import com.info.IsTakip.R;

import java.util.ArrayList;

public class EskiGorevler extends AppCompatActivity {
    Button buttonYeniGorevv;
    RecyclerView rv;
    private EskiGorevAdapter adapter;
    private Toolbar toolbar;
    private ArrayList<Gorevler> gorevArrayList;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eski_gorevler);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("IsTakip");
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        toolbar.setLogo(R.drawable.resim1);
        setSupportActionBar(toolbar);
       rv=findViewById(R.id.rv1);
       rv.setHasFixedSize(true);
       buttonYeniGorevv=findViewById(R.id.buttonYeniGorevA);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv.setLayoutManager(layoutManager);
        Gorevler g1=new Gorevler(1,"1.BAŞLIK","1.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g2=new Gorevler(2,"2.BAŞLIK","2.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g3=new Gorevler(3,"3.BAŞLIK","3.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g4=new Gorevler(4,"4.BAŞLIK","4.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g5=new Gorevler(5,"5.BAŞLIK","5.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g6=new Gorevler(6,"6.BAŞLIK","6.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g7=new Gorevler(7,"7.BAŞLIK","7.YAPİLAN İS","23.03.2017","ACİL");
        Gorevler g8=new Gorevler(8,"8.BAŞLIK","8.YAPİLAN İS","23.03.2017","ACİL");
//dd
        gorevArrayList=new ArrayList<>();
        gorevArrayList.add(g1);
        gorevArrayList.add(g2);
        gorevArrayList.add(g3);
        gorevArrayList.add(g4);
        gorevArrayList.add(g5);
        gorevArrayList.add(g6);
        gorevArrayList.add(g7);
        gorevArrayList.add(g8);

        buttonYeniGorevv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        adapter=new EskiGorevAdapter(this,gorevArrayList);

        rv.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_BuyukEkran:
                Intent intent1=new Intent(getApplicationContext(), YaziEkranModeliActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_YaziEkran:
                Intent intent=new Intent(getApplicationContext(), BuyukEkranModeli.class);
                startActivity(intent);
                return true;
            case R.id.action_CardEkran:
                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_CardBitirilen:
                Intent intent3=new Intent(getApplicationContext(),EskiGorevler.class);
                startActivity(intent3);
                return true;
            case R.id.action_YaziEkranBitirilen:
                Intent intent4=new Intent(getApplicationContext(), YaziEkranModeliBitenActivity.class);
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

    }
}
