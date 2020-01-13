package com.info.IsTakip;

public class Gorevler {
    private int gorev_id;
    private String gorev_baslik;
    private String gorev_YapilanIs;
    private String gorev_tarih;
    private String gorev_acil;
    public Gorevler(){

    }

    public Gorevler(int gorev_id, String gorev_baslik, String gorev_YapilanIs, String gorev_tarih, String gorev_acil) {
        this.gorev_id = gorev_id;
        this.gorev_baslik = gorev_baslik;
        this.gorev_YapilanIs = gorev_YapilanIs;
        this.gorev_tarih = gorev_tarih;
        this.gorev_acil = gorev_acil;
    }
//gg
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

    public String getGorev_tarih() {
        return gorev_tarih;
    }

    public void setGorev_tarih(String gorev_tarih) {
        this.gorev_tarih = gorev_tarih;
    }

    public String getGorev_acil() {
        return gorev_acil;
    }

    public void setGorev_acil(String gorev_acil) {
        this.gorev_acil = gorev_acil;
    }
}
