package com.example.pc.ChineseChow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Daniel on 4/27/2017.
 */

public class ImgUpload extends Activity {

    ImageButton selectimage;
    Button nextButton3;
    private static final int imagerequest = 1;
    private Uri mimageUri=null;


    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_upload);

        selectimage = (ImageButton) findViewById(R.id.bt_addimage);
        nextButton3 = (Button) findViewById(R.id.nextButton3);
        final Bundle bundle = this.getIntent().getExtras();

        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallaryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                gallaryIntent.setType("image/*");
                startActivityForResult(gallaryIntent, imagerequest);
            }
        });

        nextButton3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(ImgUpload.this, IngredientsUpload.class);
                bundle.putString("imageURI", mimageUri.toString());
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == imagerequest && resultCode==RESULT_OK){
            mimageUri = data.getData();
            selectimage.setImageURI(mimageUri);
        }
    }
}
