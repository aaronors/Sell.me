package com.example.aaronors.sellme_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class PromptActivity extends AppCompatActivity {
    Bundle extras;
    String imgStr;
    ImageView itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        extras = getIntent().getExtras();
        imgStr = extras.getString("img");
        itemView = (ImageView) findViewById(R.id.itemPic);

        itemView.setImageURI(Uri.parse(imgStr));
    }

    public void postAd(View view){

        EditText eName = (EditText) findViewById(R.id.editName);
        EditText ePrice = (EditText) findViewById(R.id.editPrice);

        Intent intent = new Intent(this,MainActivity.class);

        intent.putExtra("pName",eName.getText().toString());
        intent.putExtra("pPrice","$ " + ePrice.getText().toString());
        intent.putExtra("pImg",imgStr);
        startActivity(intent);
    }
}
