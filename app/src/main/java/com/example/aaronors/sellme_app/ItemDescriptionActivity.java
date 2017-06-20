package com.example.aaronors.sellme_app;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdescription);

        Bundle extras = getIntent().getExtras();

        ImageView vImg = (ImageView) findViewById(R.id.itemPic);
        TextView vNameDesc = (TextView) findViewById(R.id.nameDesc);
        TextView vPriceDesc = (TextView) findViewById(R.id.priceDesc);

        vNameDesc.setText(extras.getString("name"));
        vPriceDesc.setText(extras.getString("price"));

        vImg.setImageURI(Uri.parse(extras.getString("img")));
    }
}
