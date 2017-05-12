package com.example.pc.ChineseChow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by Daniel on 4/29/2017.
 */

public class IngredientsUpload extends Activity {
    static int totalEditTexts = 0;
    LinearLayout container;
    Button addIngredient;
    Button buttonNext;
    ArrayList<String> ingredients;
    EditText editText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_upload);


        container = (LinearLayout) findViewById(R.id.ingredientslayout);
        addIngredient = (Button) findViewById(R.id.addIngredient);
        buttonNext = (Button) findViewById(R.id.nextButton4);
        ingredients = new ArrayList<>();
        final Bundle bundle = this.getIntent().getExtras();

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalEditTexts++;
                editText = new EditText(IngredientsUpload.this);
                editText.setTag("ingredient" + totalEditTexts);
                container.addView(editText);

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int counter = 0; counter < container.getChildCount(); counter++) {
                    if(container.getChildAt(counter) instanceof EditText)
                    ingredients.add(((EditText) container.getChildAt(counter)).getText().toString());
                }

                Intent intent = new Intent(IngredientsUpload.this, Upload.class);
                bundle.putStringArrayList("ingredientList", ingredients);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}
