package com.example.aaronors.sellme_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;

import static android.R.attr.fragment;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by aaronors.
 */

public class Recycler_Adapter extends CursorRecyclerAdapter<Recycler_Adapter.ViewHolder>{

    private Context rContext;
    private Cursor rCursor;

    // use enum for categories


    // holder sub class

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView vName;
        public TextView vPrice;
        public ImageView vImg;
        public BitmapDrawable bImg;
        public String uriString;
        public String vCategory;
        public View view;


        public ViewHolder(View v) {
            super(v);
            view = v;
            vName = (TextView) v.findViewById(R.id.name_text);
            vPrice = (TextView) v.findViewById(R.id.price_text);
            vImg = (ImageView) v.findViewById(R.id.card_img);
            bImg = (BitmapDrawable) vImg.getDrawable();

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked

                    Intent intent = new Intent(v.getContext(),ItemDescriptionActivity.class);
                    intent.putExtra("name",vName.getText().toString());
                    intent.putExtra("price",vPrice.getText().toString());
                    intent.putExtra("img",uriString);
                    intent.putExtra("category",vCategory);

                    v.getContext().startActivity(intent);

                }
            });


        }

        @Override
        public void onClick(View view) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Recycler_Adapter(Context context,Cursor cursor) {

        super(cursor);
        rContext = context;
        rCursor = cursor;
    }

    // Create new views (invoked by the layout manager) - keep
    @Override
    public Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);
        // set the view's size, margins, paddings and layout parameters



        return new ViewHolder(v);
    }


    // set holder to cursor value

    @Override
    public void onBindViewHolderCursor(ViewHolder holder, Cursor cursor) {


        holder.vName.setText(cursor.getString(1));
        holder.vPrice.setText(cursor.getString(2));
        //holder.vImg.setImageURI(Uri.parse(cursor.getString(3)));
        Glide.with(rContext)
                .load(Uri.parse(cursor.getString(3)))
                .placeholder(R.drawable.espresso)
                .into(holder.vImg);
        holder.uriString = cursor.getString(3);
        holder.vCategory = cursor.getString(4);



        // set listener here for interaction with card



    }

    @Override
    public Cursor swapCursor(Cursor newCursor) {
        return super.swapCursor(newCursor);
    }

    @Override
    public int getItemCount() {
        return rCursor.getCount();
    }

    //bitmap to byte arr

    public static byte[] bitmapToBytearr(Bitmap bitmap){
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outStream);
        return outStream.toByteArray();
    }

    //byte arr to bitmap

    public static Bitmap bytearrToBitmap(byte[] arr){
        return BitmapFactory.decodeByteArray(arr, 0, arr.length);

    }

}

/****
 *
 *
 *****/