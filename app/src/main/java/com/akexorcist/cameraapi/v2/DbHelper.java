package com.akexorcist.cameraapi.v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String databaseName = "ListCoin";
    private static final int databaseVersion = 1;
    Context mycontext;

    private static final String tableCreateSQL = "CREATE TABLE coincash(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "c5 INTEGER," +
            "c10 INTEGERc," +
            "coincount INTEGER," +
            "sumcash INTEGER," +
            "c1 INTEGER," +
            "Name TEXT," +
            "c2 INTEGER" +

            ");";


    public DbHelper( Context context) {
        super(context, databaseName, null, databaseVersion);
        this.mycontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableCreateSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
