package com.example.rushitaa.photo_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewActivity extends ActionBarActivity {

    TextView image_title;
    ImageView image;
    Cursor cursor;
    int location;
    Bitmap bitmap;
    String caption;
    String Path;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Bundle extras = getIntent().getExtras();
        image_title = (TextView) findViewById(R.id.image_title);
        image = (ImageView) findViewById(R.id.image);
        location = 0;

        if (extras != null) {
            location = extras.getInt("location");
        }

        cursor = query();
        id = cursor.getString(0);
        caption = cursor.getString(1);
        Path = cursor.getString(2);
        image_title.setText(caption);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeFile(Path, options);
        image.setImageBitmap(bitmap);
    }

    private Cursor query() {
        SQLiteDatabase db = new storeDb(this).getWritableDatabase();
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;
        String result[] = { storeDb.ID_NUM,storeDb.CAPTION,storeDb.PATH };
        Cursor pointer = db.query(storeDb.DATABASE_TABLE, result,where, whereArgs, groupBy, having, order);
        while (pointer.moveToNext()) {
            int id = pointer.getInt(0);
            id--;
            if (id == location) {

                return pointer;
            }
        }

        return pointer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.action_uninstall) {
            Uri packageURI = Uri.parse("package:com.example.rushitaa.photo_app");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(uninstallIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
