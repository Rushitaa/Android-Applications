package com.example.rushitaa.food_directory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<FoodItems> {

    private final List<FoodItems> food;

    public CustomAdapter(Context context, int resource, List<FoodItems> food) {
        super(context, resource, food);
        this.food = food;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_main, null);
        TextView textView = (TextView) row.findViewById(R.id.rowText);
        textView.setText(food.get(position).getName());

        try {
            ImageView imageView = (ImageView) row.findViewById(R.id.rowImage);
            InputStream inputStream = getContext().getAssets().open(food.get(position).getSrc());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}

