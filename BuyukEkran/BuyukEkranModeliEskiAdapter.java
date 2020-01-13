package com.info.IsTakip.BuyukEkran;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info.IsTakip.GorevTasarimBilgi.GorevTasarimBilgiActivity;
import com.info.IsTakip.Gorevler;

import com.info.IsTakip.R;

import java.util.List;

public class BuyukEkranModeliEskiAdapter extends RecyclerView.Adapter<BuyukEkranModeliEskiAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Gorevler> gorevliste;

    public BuyukEkranModeliEskiAdapter(Context mContext, List<Gorevler> gorevliste) {
        this.mContext = mContext;
        this.gorevliste = gorevliste;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_tasarim_buyukekran_bitirilenkisim,viewGroup,false);
        return new CardTasarimTutucu(view);
    }
//dd
    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu cardTasarimTutucu, int i) {
        final Gorevler gorevler=gorevliste.get(i);
        cardTasarimTutucu.textViewBaslik.setText(gorevler.getGorev_baslik());
        cardTasarimTutucu.textViewYapilacakIs.setText(gorevler.getGorev_YapilanIs());
        cardTasarimTutucu.textViewTarih.setText(gorevler.getGorev_tarih());
        cardTasarimTutucu.textViewAcil.setText(gorevler.getGorev_acil());
        cardTasarimTutucu.cardgorev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, GorevTasarimBilgiActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gorevliste.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView cardgorev;
        private TextView textViewBaslik,textViewYapilacakIs,textViewTarih,textViewAcil;
        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            cardgorev=itemView.findViewById(R.id.cardbuyukekran);
            textViewBaslik=itemView.findViewById(R.id.textViewBaslikkk);
            textViewYapilacakIs=itemView.findViewById(R.id.textViewYapilacakIs);
            textViewAcil=itemView.findViewById(R.id.textViewAcil);
            textViewTarih=itemView.findViewById(R.id.textViewTarihKimAtti);
        }
    }
}
