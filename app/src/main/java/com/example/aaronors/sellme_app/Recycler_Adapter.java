package com.example.aaronors.sellme_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by aaronors.
 */

public class Recycler_Adapter extends CursorRecyclerAdapter<Recycler_Adapter.ViewHolder>{



    // holder sub class

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView vName;
        public TextView vPrice;
        public View view;


        public ViewHolder(View v) {
            super(v);
            view = v;
            vName = (TextView) v.findViewById(R.id.name_text);
            vPrice = (TextView) v.findViewById(R.id.price_text);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked

                    Intent intent = new Intent(v.getContext(),ItemDescriptionActivity.class);
                    intent.putExtra("name",vName.getText().toString());
                    intent.putExtra("price",vPrice.getText().toString());

                    v.getContext().startActivity(intent);

                }
            });


        }

        @Override
        public void onClick(View view) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Recycler_Adapter(Cursor cursor) {
        super(cursor);
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


        // set listener here for interaction with card




    }
}

/****
 *
 *
 *****/