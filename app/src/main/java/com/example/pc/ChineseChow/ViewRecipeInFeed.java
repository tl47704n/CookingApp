package com.example.pc.ChineseChow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ViewRecipeInFeed extends AppCompatActivity {
    public ArrayAdapter<String> adapter_steps;
    public ArrayAdapter<String> adapter_ing;

    private ArrayList<String> ingre = new ArrayList<String>();
    String recipe_name;
    String image_url;
    String cooktime;
    String preptime;
    String steps;
    TextView tv_steps;
    TextView tv_preptime;
    TextView tv_recipeName;
    ImageView im_image;
    ListView lv_steps;
    ListView lv_ing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_in_feed);

        Bundle bundle = getIntent().getExtras();
        recipe_name = bundle.getString("name");
        image_url = bundle.getString("url");
        cooktime = bundle.getString("cooktime");
        preptime = bundle.getString("preptime");
        steps = bundle.getString("steps");
        ingre = bundle.getStringArrayList("ing");
       lv_steps =(ListView)findViewById(R.id.lv_step_in_feed);
        tv_recipeName = (TextView)findViewById(R.id.tv_recipe_name_in_view_feed);
        im_image = (ImageView)findViewById(R.id.im_image_in_view_feed);
        tv_preptime = (TextView)findViewById(R.id.tv_preptime_in_view_feed);

        lv_ing=(ListView)findViewById(R.id.lv_in_feed);
        tv_recipeName.setText(recipe_name);
        tv_preptime.setText("Prepare Time: " + preptime+ "\n"  + "Cook Time: " + cooktime);

        String[] steps_list = steps.split("\n");
        adapter_steps = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,steps_list);

        adapter_ing = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ingre);
        lv_steps.setAdapter(adapter_steps);
        lv_steps.setFocusable(false);
       lv_ing.setAdapter(adapter_ing);
        lv_ing.setFocusable(false);



       Picasso.with(this).load(image_url).into(im_image);

    }
}
