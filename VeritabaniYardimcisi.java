package com.info.IsTakip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeritabaniYardimcisi extends SQLiteOpenHelper {
    public VeritabaniYardimcisi(Context context) {
        super(context, "sozluk", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"kelimeler\" (\n" +
                "\t\"kelime_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"varyok\"\tTEXT\n" +
                ");");
    }
//gg
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS kelimeler");
onCreate(db);
    }
}
