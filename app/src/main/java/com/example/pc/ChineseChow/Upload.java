package com.example.pc.ChineseChow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by pc on 2017/3/19.
 */

public class Upload extends Activity {

    public static HashMap<String,String> map = new HashMap<String, String>();
    Button recipe_upload;
    EditText recipeSteps;
    Firebase main_ref;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    String downloadUri_string;
    static String linkfortest;
    private StorageReference mStorage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipesteps_upload);

        mStorage = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Recipes");

        recipe_upload = (Button) findViewById(R.id.bt_upload);
        main_ref = new Firebase("https://homechefparty-e0f77.firebaseio.com/");
        recipeSteps = (EditText) findViewById(R.id.recipeSteps);



        recipe_upload.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                startposting();

            }



        });

    }



    private void startposting(){


        final ProgressDialog progressdialog = new ProgressDialog(Upload.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
        Bundle bundle = this.getIntent().getExtras();
        Uri mimageUri= Uri.parse(bundle.getString("imageURI"));
        final String getrecipeName = bundle.getString("name");
        final String getcookTime = bundle.getString("cooktime");
        final String getprepTime = bundle.getString("preptime");
        final String getRecipeSteps = recipeSteps.getText().toString();
        final ArrayList<String> getIngredients = bundle.getStringArrayList("ingredientList");


        if(mimageUri!=null){
            StorageReference filepath =  mStorage.child("Recipe_Images").child(mimageUri.getLastPathSegment());
            filepath.putFile(mimageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests") Uri fromgallery  = taskSnapshot.getDownloadUrl();


                  //  @SuppressWarnings("VisibleForTests") Uri fromgallary  = taskSnapshot.getDownloadUrl();


                    DatabaseReference newpost = databaseReference.push();
                    linkfortest = newpost.getRef().toString();
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    Log.i("Uri",linkfortest);
                    /*Intent i = new Intent(Upload.this,ViewRecipeInSearch.class);
                    i.putExtra("linkForTest",linkfortest);
                    i.putExtra("namefortest",getrecipeName);
                    startActivity(i); */



                    downloadUri_string = fromgallery.toString();
                    newpost.child("imageUri").setValue(fromgallery.toString());
                    newpost.child("recipeName").setValue(getrecipeName);
                    newpost.child("cookTime").setValue(getcookTime);
                    newpost.child("prepTime").setValue(getprepTime);
                    newpost.child("recipe").setValue(getRecipeSteps);
                    newpost.child("Ingredients").setValue(getIngredients);

                }
            });
        }
        Recipe r = new Recipe();


        r.setIngredients(getIngredients);
        r.setRecipeName(getrecipeName);
        r.setCookTime(getcookTime);
        r.setPrepTime(getprepTime);
        r.setRecipe(getRecipeSteps);
        r.setImageUri(downloadUri_string);

        progressdialog.dismiss();

        Intent intent = new Intent(Upload.this,RecipeFeed.class);
        startActivity(intent);


    }


}

