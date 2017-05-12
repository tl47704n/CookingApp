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

public class CookPrepUpload extends Activity {

    Button nextButton2;
    EditText cookTime;
    EditText prepTime;


    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookprep_upload);

        nextButton2 = (Button) findViewById(R.id.nextButton2);
        cookTime = (EditText) findViewById(R.id.cookTime);
        prepTime = (EditText) findViewById(R.id.prepTime);
        final Bundle bundle = this.getIntent().getExtras();

        nextButton2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                Intent imgUpload = new Intent(CookPrepUpload.this, ImgUpload.class);
                bundle.putString("cooktime", cookTime.getText().toString());
                bundle.putString("preptime", prepTime.getText().toString());
                imgUpload.putExtras(bundle);
                startActivity(imgUpload);



            }

        });

    }


}
