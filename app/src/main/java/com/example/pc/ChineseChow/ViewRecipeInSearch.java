package com.example.pc.ChineseChow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;



/**
 * Created by pc on 2017/4/14.
 */

public class ViewRecipeInSearch extends Activity {
    public ArrayAdapter<String> adapter_steps;
    public ArrayAdapter<String> adapter_ing;

    int position=0;
   ImageView imageView;
    TextView tv_name;
    TextView tv_prep_cook_time;
    ListView lv_ing;
    ListView lv_steps;
    Button tomain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_recipe);
    imageView=(ImageView)findViewById(R.id.im_image_in_view_search);
        tv_name=(TextView)findViewById(R.id.tv_recipe_name_in_search);
        tv_prep_cook_time=(TextView)findViewById(R.id.tv_preptime_in_view_search);
        lv_ing = (ListView)findViewById(R.id.lv_ingredients_search);
        lv_steps = (ListView)findViewById(R.id.lv_step_search);
        tomain = (Button)findViewById(R.id.bt_tomain);
      Bundle bundle = getIntent().getExtras();
       /*  String url = bundle.getString("url");
        String name = bundle.getString("name");
        String cook_time = bundle.getString("cooktime");
        String prep_time = bundle.getString("preptime");
         ArrayList<String> ing_list = new ArrayList<String>();
        ing_list = bundle.getStringArrayList("ing");
        String steps = bundle.getString("steps");*/
       position = bundle.getInt("position");
        String[] steps_list = Search.recipes.get(position).getRecipe().split("\n");
        Picasso.with(this).load(Search.recipes.get(position).getImageUri()).into(imageView);
        tv_name.setText(Search.recipes.get(position).getRecipeName());
        tv_prep_cook_time.setText("Prepare Time: " + Search.recipes.get(position).getPrepTime()
                                        + "\n"  + "Cook Time: " + Search.recipes.get(position).getCookTime());
        adapter_steps = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,steps_list);
        adapter_ing = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Search.recipes.get(position).getIngredients());
        lv_ing.setAdapter(adapter_ing);
        lv_ing.setFocusable(false);
        lv_steps.setAdapter(adapter_steps);
        lv_steps.setFocusable(false);

        tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewRecipeInSearch.this,RecipeFeed.class);
                startActivity(i);
            }
        });









    }
}
