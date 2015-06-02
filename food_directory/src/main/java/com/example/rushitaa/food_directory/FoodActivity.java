package com.example.rushitaa.food_directory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class FoodActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        final Button button = (Button) findViewById(R.id.dial_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                call();
            }
        });

    }

    private void call() {
        String myData = "tel:408 429 3930";
        Intent myActivity = new Intent(Intent.ACTION_CALL, Uri.parse(myData));
        startActivity(myActivity);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.food_info) {
            Intent intent = new Intent(this, FoodActivity.class);
            startActivity(intent);

            return true;
        }
        else if(id == R.id.uninstall) {
            Uri packageURI = Uri.parse("package:com.example.rushitaa.food_directory");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(uninstallIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
