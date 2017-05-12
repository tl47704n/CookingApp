package com.example.pc.ChineseChow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Daniel on 4/27/2017.
 */

public class NameUpload extends Activity {

     Button nextButton1;
     EditText recipeName;


    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_upload);

        nextButton1 = (Button) findViewById(R.id.nextButton);
        recipeName = (EditText) findViewById(R.id.recipeName);


        nextButton1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Bundle bundle = new Bundle();
                Intent cookPrep = new Intent(NameUpload.this, CookPrepUpload.class);
                bundle.putString("name", recipeName.getText().toString());
                cookPrep.putExtras(bundle);
                startActivity(cookPrep);



            }

        });

    }
}
