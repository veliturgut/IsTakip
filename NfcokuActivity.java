package com.info.IsTakip;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.info.IsTakip.R;

public class NfcokuActivity extends AppCompatActivity {

    private TextView textViewNfc,textViewNfcOkundumu,textViewNfcKodIsmi;
    private ImageView imageViewNFC,imageViewTik;
    private Button buttonBitir;
    String tagid;

    private long BackpressedTime = 0;
    NfcAdapter nfcAdapter;
    TextView textView;
    private Animation animasyon;
    private SharedPreferences sp;
    private SharedPreferences.Editor e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcoku);
        imageViewTik=findViewById(R.id.imageViewTik);
        animasyon = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
        buttonBitir=findViewById(R.id.buttonBitir);
        textViewNfc=findViewById(R.id.textViewNfc);
        textViewNfcOkundumu=findViewById(R.id.textViewNfcOkundumu);
        textViewNfcKodIsmi=findViewById(R.id.textViewNfcKodIsmi);
        imageViewNFC=findViewById(R.id.imageViewNFC);
//gg
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//tekrar :D
        SharedPreferences.Editor e=sp.edit(); // her değişm sonrası veriyi sıfırlamak makul bi testini ypar mısın
        e.putBoolean("nfcVar",false);// burda ise resim çekildimi onu aldım haha buras ture olursa olur tabi :d
        e.apply();


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);


        if (nfcAdapter != null && nfcAdapter.isEnabled()){
            System.out.println("nfcAdapter.isenabled  " + nfcAdapter.isEnabled());
            Toast.makeText(this,"NFC available!",Toast.LENGTH_LONG).show();
            System.out.println("nfcadapterextra tag " + NfcAdapter.EXTRA_ID.toString());
            System.out.println("Nfcmanager.class   " + NfcManager.class.toString());
            //Tag myTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //onNewIntent();
        }
        else
        {
            Toast.makeText(NfcokuActivity.this,"NFC not available :(",Toast.LENGTH_LONG).show();
            // finish();//programı kapatır...
        }
    }


    final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        System.out.println("hexchars :  " + hexChars);
        return new String(hexChars);

    }



    @Override
    protected void onNewIntent(Intent intent) {

        final Double[] a = new Double[1];
        //
        // this.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG))
        {
            Toast.makeText(this,"NFCintent!",Toast.LENGTH_LONG).show();
            //System.out.println("buradayız");
            //Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Tag myTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            // Log.i("tag ID", myTag.getId().toString());
            //NdefMessage ndefMessage = createNdefMessage("My string content!");
            //bytesToHexString(NfcAdapter.EXTRA_TAG);
            //writeNdefMessage(tag,ndefMessage);
            imageViewTik.startAnimation(animasyon);
            System.out.println("tag id :  " + myTag.getId().toString());
            tagid = bytesToHex(myTag.getId());
            System.out.println("bytestohex :: " + bytesToHex(myTag.getId()));
            textViewNfcOkundumu.setText("NFC başarılı bir şekilde okundu.");
            SharedPreferences.Editor e=sp.edit();
            e.putBoolean("nfcVar",true);// burda ise resim çekildimi onu aldım
            e.apply();

            CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                   onBackPressed();
                }
            }.start();


            /*Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            intent1.putExtra("send_string",tagid);
            startActivity(intent1);*/
            //= ref1.setValue("Tags/");

            //System.out.println("ref1.child   "+ref1.child("Tags").getDatabase());
            //System.out.println(""+ref1.equals(tagid));






        }


    }




    @Override
    protected void onResume() {

        enableForegroundDispatchSystem();


        super.onResume();
    }

    @Override
    protected void onPause() {

        disableForegroundDispatchSystem();


        super.onPause();
    }


    private void enableForegroundDispatchSystem(){

        Intent intent = new Intent(NfcokuActivity.this,NfcokuActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        IntentFilter[] intentfilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(NfcokuActivity.this,pendingIntent,intentfilters,null);

    }
    private void disableForegroundDispatchSystem(){

        nfcAdapter.disableForegroundDispatch(NfcokuActivity.this);
    }
}
