package com.example.aaronors.sellme_app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by aaronors on 6/19/2017.
 */

public class PromptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        Bundle extras = getIntent().getExtras();

        String imgStr = extras.getString("img");

        ImageView itemView = (ImageView) findViewById(R.id.itemPic);

/*        Glide.with(this)
                .load(Uri.parse(imgStr))
                //.placeholder(R.drawable.pic01)
                .into(itemView);*/

        itemView.setImageURI(Uri.parse(imgStr));

    }
}
