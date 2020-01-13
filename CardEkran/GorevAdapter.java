package com.info.IsTakip.CardEkran;

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

import static com.info.IsTakip.R.color.darkkirmizi;
import static com.info.IsTakip.R.color.kirmizi;
import static com.info.IsTakip.R.color.sari;
import java.util.List;



public class GorevAdapter extends RecyclerView.Adapter<GorevAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<Gorevler> gorevliste;

    public GorevAdapter(Context mContext, List<Gorevler> gorevliste) {
        this.mContext = mContext;
        this.gorevliste = gorevliste;
    }


    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_tasarim_atanankisim,viewGroup,false);
        return new CardTasarimTutucu(view);
    }

//dd
    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu cardTasarimTutucu, final int i) {
        final Gorevler gorevler=gorevliste.get(i);
        cardTasarimTutucu.textViewBaslik.setText(gorevler.getGorev_baslik());
        cardTasarimTutucu.textViewYapilacakIs.setText(gorevler.getGorev_YapilanIs());
        cardTasarimTutucu.textViewAcil.setText(gorevler.getGorev_acil());
        cardTasarimTutucu.textViewTarih.setText(gorevler.getGorev_tarih());
        cardTasarimTutucu.cardgorev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, GorevTasarimBilgiActivity.class);
                mContext.startActivity(intent);
            }
        });

        if (cardTasarimTutucu.textViewAcil.getText()=="ACİL"){
            cardTasarimTutucu.cardgorev.setCardBackgroundColor(mContext.getResources().getColor(darkkirmizi));
        }
        else if (cardTasarimTutucu.textViewAcil.getText()=="NORMAL"){
            cardTasarimTutucu.cardgorev.setCardBackgroundColor(mContext.getResources().getColor(sari));
        }
        else if (cardTasarimTutucu.textViewAcil.getText()=="ÇOK ACİL"){
            cardTasarimTutucu.cardgorev.setCardBackgroundColor(mContext.getResources().getColor(kirmizi));
        }
    }

    @Override
    public int getItemCount() {
        return gorevliste.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewYapilacakIs,textViewAcil,textViewTarih,textViewBaslik;
        private CardView cardgorev;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            textViewYapilacakIs=itemView.findViewById(R.id.textViewYapilacakIs);
            textViewAcil=itemView.findViewById(R.id.textViewAcil);
            textViewBaslik=itemView.findViewById(R.id.textViewBaslikkk);
            textViewTarih=itemView.findViewById(R.id.textViewTarihKimAtti);
            cardgorev=itemView.findViewById(R.id.cvAtanan);



        }
    }
}
