package com.example.rushitaa.food_directory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;

public class FoodDetailActivity extends ActionBarActivity {
    private TextView displayName;
    private ImageView displayImage;
    private TextView displayDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        displayName = (TextView) findViewById(R.id.nameTextView);
        displayDesc = (TextView)findViewById(R.id.descTextView);
        String item = getIntent().getExtras().getString("item");
        String image = getIntent().getExtras().getString("image");
        String description = getIntent().getExtras().getString("description");
        displayName.setText(item);
        try {
            displayImage = (ImageView)findViewById(R.id.imageView);
            InputStream inputStream = getAssets().open(image);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            displayImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayDesc.setText(description);


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
