package com.info.IsTakip.GorevTasarimBilgi;

public class DetayGörevler {
    private int gorev_id;
    private String gorev_baslik;
    private String gorev_YapilanIs;
    private String gorev_NfcYer;
    private boolean nfcvaryok;
    private boolean varyok;
public DetayGörevler(){

}
    public DetayGörevler(int gorev_id, String gorev_baslik, String gorev_YapilanIs, String gorev_NfcYer, boolean nfcvaryok, boolean varyok) {
        this.gorev_id = gorev_id;
        this.gorev_baslik = gorev_baslik;
        this.gorev_YapilanIs = gorev_YapilanIs;
        this.gorev_NfcYer = gorev_NfcYer;
        this.nfcvaryok = nfcvaryok;
        this.varyok = varyok;
    }
//dd
    public int getGorev_id() {
        return gorev_id;
    }

    public void setGorev_id(int gorev_id) {
        this.gorev_id = gorev_id;
    }

    public String getGorev_baslik() {
        return gorev_baslik;
    }

    public void setGorev_baslik(String gorev_baslik) {
        this.gorev_baslik = gorev_baslik;
    }

    public String getGorev_YapilanIs() {
        return gorev_YapilanIs;
    }

    public void setGorev_YapilanIs(String gorev_YapilanIs) {
        this.gorev_YapilanIs = gorev_YapilanIs;
    }

    public String getGorev_NfcYer() {
        return gorev_NfcYer;
    }

    public void setGorev_NfcYer(String gorev_NfcYer) {
        this.gorev_NfcYer = gorev_NfcYer;
    }

    public boolean isNfcvaryok() {
        return nfcvaryok;
    }

    public void setNfcvaryok(boolean nfcvaryok) {
        this.nfcvaryok = nfcvaryok;
    }

    public boolean isVaryok() {
        return varyok;
    }

    public void setVaryok(boolean varyok) {
        this.varyok = varyok;
    }
}
