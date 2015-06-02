package com.example.rushitaa.photo_app;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class AddPhotoActivity extends ActionBarActivity {

    EditText caption;
    ImageView picture;
    Button capture;
    Button save;
    Bitmap bitmap;
    String name;
    String path;
    int count = 0;
    private static final int REQUEST_TAKE_PHOTO = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        caption = (EditText) findViewById(R.id.editText);
        picture = (ImageView) findViewById(R.id.imageView);
        capture = (Button) findViewById(R.id.Picture);
        save = (Button) findViewById(R.id.Save);
        File Directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"photo_app");
        Directory.mkdir();
        path = Directory.getAbsolutePath() + "/" + System.currentTimeMillis()+ ".jpg";
    }

    public void capture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(path)));
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    public void save(View view) {

        if (count == 0) {
            Toast.makeText(getApplicationContext(), "No image",
                    Toast.LENGTH_LONG).show();

        }

        name = caption.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "No caption",
                    Toast.LENGTH_LONG).show();
        }

        if (count != 0 && name.length() != 0) {

            SQLiteDatabase data = new storeDb(this) .getWritableDatabase();
            ContentValues newValues = new ContentValues();
            newValues.put(storeDb.CAPTION, name);
            newValues.put(storeDb.PATH, path);
            data.insert(storeDb.DATABASE_TABLE, null, newValues);
            finish();

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeFile(path, options);
        picture.setImageBitmap(bitmap);
        count = 1;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_photo, menu);
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
