package com.example.rushitaa.food_directory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements MyQuestionDialogFragment.MyQuestionListener{
    private List<FoodItems> food = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listView = (ListView) findViewById(R.id.list);
        food.add(new FoodItems("Baby Corn", "babycorn.jpg","Crispy and fried."));
        food.add(new FoodItems("Hakka Noodles", "noodles.jpg","All Veggies included."));
        food.add(new FoodItems("Manchow Soup", "manchow.jpg","Hot served with crispy noodles."));
        food.add(new FoodItems("Wontons", "wontons.jpg","Noodles filling and deep fried."));
        food.add(new FoodItems("Chilli Paneer", "paneer.jpg","Spicy and marinated."));
        listView.setAdapter(new CustomAdapter(this, R.layout.activity_main, food));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listView.getCount()-1==position){
                    MyQuestionDialogFragment fragment = new MyQuestionDialogFragment();
                    fragment.show(getSupportFragmentManager(),"question");
                }
                else {
                    String Item = food.get(position).getName();
                    String Image = food.get(position).getSrc();
                    String Description = food.get(position).getDesc();

                    Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                    intent.putExtra("item", Item);
                    intent.putExtra("image", Image);
                    intent.putExtra("description", Description);


                    startActivity(intent);
                }
            }
        });
    }



    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        int position = listView.getCount()-1;
        String Item = food.get(position).getName();
        String Image = food.get(position).getSrc();
        String Description = food.get(position).getDesc();
        Intent newIntent = new Intent(MainActivity.this,FoodDetailActivity.class);
        newIntent.putExtra("item", Item);
        newIntent.putExtra("image", Image);
        newIntent.putExtra("description", Description);
        startActivity(newIntent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

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





