package com.info.IsTakip.GorevTasarimBilgi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.info.IsTakip.CameraActivity;
import com.info.IsTakip.NfcokuActivity;

import com.info.IsTakip.gps;
import com.info.IsTakip.R;

import java.util.List;
public class GorevTasarimBilgiAdapter extends RecyclerView.Adapter<GorevTasarimBilgiAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<DetayGörevler> detayGörevlerList;
    private CameraActivity cameraActivity;
    private String ad;
    gps konumServisi;
    private int izinKontrol;
    private SharedPreferences sp;
    private SharedPreferences.Editor e;
    private CheckBox cb;
    public GorevTasarimBilgiAdapter(Context mContext, List<DetayGörevler> detayGörevlerList) {
        this.mContext = mContext;
        this.detayGörevlerList = detayGörevlerList;
    }//dd
    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_gorevtasarimbilgi, viewGroup, false);
        return new CardTasarimTutucu(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu cardTasarimTutucu, final int i) {
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final DetayGörevler gorevler = detayGörevlerList.get(i);
        cardTasarimTutucu.textViewBaslikGorevBilgi.setText(gorevler.getGorev_baslik());
        cardTasarimTutucu.checkBox1.setText(gorevler.getGorev_YapilanIs());
        cardTasarimTutucu.textViewNfcYer.setText(gorevler.getGorev_NfcYer());
        cardTasarimTutucu.imageViewkamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = sp.edit();
                e.putInt("cbIndex", i);
                e.apply();
                Intent intent = new Intent(mContext, CameraActivity.class);
                mContext.startActivity(intent);
            }
        });
        cardTasarimTutucu.imageViewnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NfcokuActivity.class);
                mContext.startActivity(intent);
            }
        });
        cardTasarimTutucu.checkBox1.setChecked(gorevler.isVaryok()&&gorevler.isNfcvaryok());
        final String[] k = new String[]{"kamera","nfc","gps"};
        cardTasarimTutucu.imageViewkamera.setVisibility(View.INVISIBLE);
        cardTasarimTutucu.imageViewGpsResim.setVisibility(View.INVISIBLE);
        cardTasarimTutucu.imageViewnfc.setVisibility(View.INVISIBLE);
        for (int b = 0; b < k.length; b++) {
            if (k[b] == "nfc") {
                cardTasarimTutucu.imageViewnfc.setVisibility(View.VISIBLE);
                if (cardTasarimTutucu.checkBox1.equals(true) && k.length>=1){
                    cardTasarimTutucu.checkBox1.setChecked(false);
                    cardTasarimTutucu.checkBox1.setChecked(gorevler.isNfcvaryok());
                }
                else if (k.length == 1){
                    cardTasarimTutucu.checkBox1.setChecked(gorevler.isNfcvaryok());
                }
            }
            if (k[b] == "kamera") {
                cardTasarimTutucu.imageViewkamera.setVisibility(View.VISIBLE);
                if (cardTasarimTutucu.checkBox1.equals(true)&& k.length>=1) {
                    cardTasarimTutucu.checkBox1.setChecked(false);
                    cardTasarimTutucu.checkBox1.setChecked(gorevler.isVaryok());
                }
                else if (k.length == 1){
                    cardTasarimTutucu.checkBox1.setChecked(gorevler.isVaryok());
                }
            }
            if (k[b] == "gps") {
                cardTasarimTutucu.imageViewGpsResim.setVisibility(View.VISIBLE);izinKontrol= ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
                if (izinKontrol!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) mContext,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
                }
                konumServisi = new gps(mContext);
                if(konumServisi.getKonumaErisim()){
                    double enlem=konumServisi.getEnlem();
                    double boylam=konumServisi.getBoylam();
                    Log.d("KONUM_SERVISI","ENLEM: "+enlem);
                    Log.d("KONUM_SERVISI","BOYLAM: "+boylam);
                }
                else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                    adb.setTitle("GPS ayarları");
                    adb.setMessage("Konum servisi kapalıdır. Açmak için Düzenle'ye tıklayınız.");
                    adb.setPositiveButton("Düzenle", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            mContext.startActivity(intent);
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
        cardTasarimTutucu.checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < k.length; i++) {
                    if (k[i] == "kamera") {
                        Intent intent = new Intent(mContext, CameraActivity.class);
                        mContext.startActivity(intent);
                    }
                    if (k[i] == "nfc") {
                        Intent intent1 = new Intent(mContext, NfcokuActivity.class);
                        mContext.startActivity(intent1);
                    }
                    if (k[i] == "gps") {
                        Toast.makeText(mContext, "Gps okundu", Toast.LENGTH_SHORT).show();
                    }
                }
                e = sp.edit();
                e.putInt("cbIndex", i);
                e.apply();
                notifyDataSetChanged();
            }
        });
    }
    public void cbTikKontol(int index, boolean varMı) {
        if (index != -1) {
            detayGörevlerList.get(index).setVaryok(varMı);

            notifyDataSetChanged();
        }
    }
    public void cbTikkkKontrol(int index,boolean varMı){
        if (index != -1) {
            detayGörevlerList.get(index).setNfcvaryok(varMı);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return detayGörevlerList.size();
    }
    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewvaryok;
        private CardView cardgorevtasarim;
        private TextView textViewnfcvaryok;
        private TextView textViewBaslikGorevBilgi;
        private TextView textViewNfcYer;
        private ImageView imageViewkamera, imageViewnfc, imageViewGpsResim;
        private CheckBox checkBox1;
        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            textViewnfcvaryok=itemView.findViewById(R.id.textViewnfcvaryok);
            textViewvaryok = itemView.findViewById(R.id.textViewvaryok);
            imageViewkamera = itemView.findViewById(R.id.imageViewkamera);
            imageViewnfc = itemView.findViewById(R.id.imageViewnfc);
            textViewBaslikGorevBilgi = itemView.findViewById(R.id.textViewBaslikGorevBilgi);
            checkBox1 = itemView.findViewById(R.id.checkBox1);
            textViewNfcYer = itemView.findViewById(R.id.textViewNfcYer);
            imageViewGpsResim = itemView.findViewById(R.id.imageViewGpsResim);
            cardgorevtasarim = itemView.findViewById(R.id.cardgorevtasarim);
        }
    }
}
