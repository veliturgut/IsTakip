package com.info.IsTakip.BuyukEkran;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.info.IsTakip.CardEkran.EskiGorevler;
import com.info.IsTakip.Gorevler;
import com.info.IsTakip.CardEkran.MainActivity;

import com.info.IsTakip.YaziEkran.YaziEkranModeliActivity;
import com.info.IsTakip.YaziEkran.YaziEkranModeliBitenActivity;
import com.info.IsTakip.gps;
import com.info.IsTakip.R;

import java.util.ArrayList;

public class BuyukEkranModeli extends AppCompatActivity {
    RecyclerView rv;
    private Toolbar toolbar;
    private BuyukEkranModeliAdapter adapter;
    private ArrayList<Gorevler> gorevArrayList;
    private Button buttonEski;
    //gps
    gps konumServisi;
    private int izinKontrol;
    //gps
    String imei;
    String modeli;
    String numara;
    String TAG = "PhoneActivityTAG";
    Activity activity = BuyukEkranModeli.this;
    String wantPermission = Manifest.permission.READ_PHONE_STATE;
    private static final int PERMISSION_REQUEST_CODE = 1;

//dd

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyuk_ekran_modeli);
        toolbar=findViewById(R.id.toolbar3);
        toolbar.setLogo(R.drawable.resim1);
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        toolbar.setTitle("IsTakip");
        setSupportActionBar(toolbar);
        buttonEski=findViewById(R.id.buttonBitennGorev);
        buttonEski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), buyuk_ekran_modelieskiActivity.class);
                startActivity(intent);
            }
        });
        rv=findViewById(R.id.rv1);
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
        gorevArrayList=new ArrayList<>();gorevArrayList.add(g1);gorevArrayList.add(g2);gorevArrayList.add(g3);gorevArrayList.add(g4);gorevArrayList.add(g5);
        gorevArrayList.add(g6);gorevArrayList.add(g7);gorevArrayList.add(g8);adapter=new BuyukEkranModeliAdapter(this,gorevArrayList);
        rv.setAdapter(adapter);
        //kisiEkle();
        if (!checkPermission(wantPermission)) {
            requestPermission(wantPermission);
        } else {
            Log.d(TAG, "marrPhone number: " + getPhone());
        }
        System.out.println("device name" +getDeviceModel());
        //gps
        /*izinKontrol= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (izinKontrol!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(BuyukEkranModeli.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
        }
        konumServisi = new gps(getApplicationContext());
        if(konumServisi.getKonumaErisim()){
            double enlem=konumServisi.getEnlem();
            double boylam=konumServisi.getBoylam();
            Log.d("KONUM_SERVISI","ENLEM: "+enlem);
            Log.d("KONUM_SERVISI","BOYLAM: "+boylam);
        }
        else{

            AlertDialog.Builder adb = new AlertDialog.Builder(BuyukEkranModeli.this);
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
        */
        //gps
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
                Intent intent=new Intent(getApplicationContext(),BuyukEkranModeli.class);
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
                Intent intent4=new Intent(getApplicationContext(), YaziEkranModeliBitenActivity.class);
                startActivity(intent4);
                return true;
            case R.id.action_BuyukEkranBitirilen:
                Intent intent5=new Intent(getApplicationContext(),buyuk_ekran_modelieskiActivity.class);
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

    //****************************************************
    String getDeviceModel() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        System.out.println("marr : " + Build.BRAND);
        System.out.println("marr : " + Build.MODEL);
        System.out.println("marr : " + Build.MANUFACTURER);
        modeli = Build.BRAND;


        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getPhone() {
        TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, wantPermission) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        imei = phoneMgr.getImei();
        numara=phoneMgr.getLine1Number();
        System.out.println("imei1 "+phoneMgr.getImei());
        //System.out.println("imei2 "+phoneMgr.getPhoneType());
        //System.out.println("ime3 " + phoneMgr.getDeviceSoftwareVersion());
        //System.out.println("ime3 " + phoneMgr.getLine1Number());//numara
        // System.out.println("imei3 "+phoneMgr.getAllCellInfo());

        return phoneMgr.getLine1Number();

    }
    private void requestPermission(String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
            Toast.makeText(activity, "Phone state permission allows us to get phone number. Please allow it for additional functionality.", Toast.LENGTH_LONG).show();
        }                                //Telefon durumu izni telefon numarası almamızı sağlar. Lütfen ek işlevsellik için izin verin.
        ActivityCompat.requestPermissions(activity, new String[]{permission},PERMISSION_REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Phone number: " + getPhone());
                } else {
                    Toast.makeText(activity,"Permission Denied. We can't get phone number.", Toast.LENGTH_LONG).show();//İzin reddedildi. Telefon numarası alamıyoruz.
                }
                break;
        }
        if (requestCode==100){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"GPS'E İZİN VERİLDİ",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"GPS'E İZİN VERİLMEDİ",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean checkPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(activity, permission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


//***********************************************
   /*public void kisiEkle(){
        //String url="http://www.elzempen.com/deneme/insert_kisiler.php";
        String url="https://perapp.vaultunified.com/login";
        StringRequest istek=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("imei",imeş);
                params.put("password",password);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);
    }*/
}
