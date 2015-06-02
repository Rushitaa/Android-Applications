package com.example.rushitaa.photo_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private List<String> getItems() {

        List<String> list = new ArrayList<>();
        SQLiteDatabase db = new storeDb(this).getWritableDatabase();
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;
        String[] result = { storeDb.ID_NUM, storeDb.CAPTION, storeDb.PATH };
        Cursor cursor = db.query(storeDb.DATABASE_TABLE, result, where, whereArgs, groupBy, having, order);
        while (cursor.moveToNext()) {
            String caption = cursor.getString(1);
            list.add(caption);
        }

        return list;
    }

    public void ViewList() {

        List<String> items = getItems();

        ListView listView = (ListView) findViewById(R.id.list);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {

                Intent view_photo = new Intent(MainActivity.this, ViewActivity.class);
                view_photo.putExtra("location", position);
                startActivity(view_photo);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_photo) {
            Intent intent = new Intent(this, AddPhotoActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_uninstall) {
            Uri packageURI = Uri.parse("package:com.example.rushitaa.photo_app");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(uninstallIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);

   }
    @Override
    protected void onResume() {

        super.onResume();
        ViewList();

    }

}
