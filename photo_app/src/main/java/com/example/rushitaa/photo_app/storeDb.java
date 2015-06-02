package com.example.rushitaa.photo_app;

/**
 * Created by Rushitaa on 2/7/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class storeDb extends SQLiteOpenHelper {

    public static final String ID_NUM = "_id";
    public static final String CAPTION= "CAPTION_COLUMN";
    public static final String PATH = "FILE_PATH_COLUMN";

    public static final String DATABASE_TABLE = "Gallery";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = String.format(
            "CREATE TABLE %s (" + "  %s integer primary key autoincrement, "
                    + "  %s text," + "  %s text)", DATABASE_TABLE, ID_NUM,
            CAPTION, PATH);

    public storeDb(Context context) {
        super(context, DATABASE_TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase data) {
        data.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase data, int oldVersion, int newVersion) {
        data.execSQL("DROP TABLE IF IT EXITS" + DATABASE_TABLE);
        onCreate(data);
    }
}

