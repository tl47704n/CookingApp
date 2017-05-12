package com.example.pc.ChineseChow;


import android.content.Intent;
import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    String recipeName_from_search;
    String cooktime_from_search;
    String preptime_from_search;
    String steps_from_search;
    ArrayList<String> ingredients_from_search;
    public static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    String url;
    private ArrayList<String> recipe_names_list = new ArrayList<String>();
    public ArrayAdapter<String> adapter_search;

    ListView lv;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
         lv = (ListView) findViewById(R.id.ListView);
        adapter_search = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, recipe_names_list);



        final DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReferenceFromUrl("https://homechefparty-e0f77.firebaseio.com/Recipes");

       // getSupportActionBar().setDisplayShowTitleEnabled(false);

        databaseReference.orderByChild("recipeName").addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Recipe value = dataSnapshot.getValue(Recipe.class);
                recipe_names_list.add(value.getRecipeName());
                adapter_search.notifyDataSetChanged();
                recipes.add(value);
                recipeName_from_search = value.getRecipeName();
                cooktime_from_search = value.getCookTime();
                preptime_from_search = value.getPrepTime();
                steps_from_search = value.getRecipe();
                ingredients_from_search = value.getIngredients();
                url = value.getImageUri();




            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        lv.setAdapter(adapter_search);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Search.this,ViewRecipeInSearch.class);
           /*     intent.putExtra("name",recipeName_from_search);
                intent.putExtra("cooktime",cooktime_from_search);
                intent.putExtra("preptime",preptime_from_search);
                intent.putExtra("url",url);
                intent.putExtra("steps",steps_from_search);
                intent.putStringArrayListExtra("ing", ingredients_from_search);*/
           int position = i;
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menuSearch));
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_search.getFilter().filter(newText);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}
