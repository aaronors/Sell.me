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
 *
 */

public class Recycler_Adapter extends CursorRecyclerAdapter<Recycler_Adapter.ViewHolder>{
    private Context rContext;
    private Cursor rCursor;


    /**
     * The ViewHolder class is used to hold "card" View data as a unit
     * until it is needed by the RecyclerView.
     * Sets click functionality for each "card".
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

    public Recycler_Adapter(Context context,Cursor cursor) {
        super(cursor);
        rContext = context;
        rCursor = cursor;
    }

    @Override
    public Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);

        return new ViewHolder(v);
    }

    /**
     * Sets data from db cursor query to a "card".
     * onBindViewHolderCursor is called only when a new "card" is needed to be
     * loaded onto the screen.
     */
    @Override
    public void onBindViewHolderCursor(ViewHolder holder, Cursor cursor) {
        holder.vName.setText(cursor.getString(1));
        holder.vPrice.setText(cursor.getString(2));
        Glide.with(rContext)
                .load(Uri.parse(cursor.getString(3)))
                .placeholder(R.drawable.espresso)
                .into(holder.vImg);
        holder.uriString = cursor.getString(3);
        holder.vCategory = cursor.getString(4);
    }

    @Override
    public int getItemCount() {
        return rCursor.getCount();
    }

}

