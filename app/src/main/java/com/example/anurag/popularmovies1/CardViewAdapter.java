package com.example.anurag.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anurag.popularmovies1.Api.Model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anurag on 7/9/2016.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.MyHolder> {

    static List<Result> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public CardViewAdapter(Context context, List<Result> list) {


        this.context = context;
        clearData();
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void clearData() {
        int size = this.list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.list.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final Result current = list.get(position);
        Log.d("poster", "  : " + current.getPoster_path());
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + current.getPoster_path()).into(holder.posterImg);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailsActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();

    }

    class MyHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView posterImg;

        public MyHolder(View itemView) {
            super(itemView);
            posterImg = (ImageView) itemView.findViewById(R.id.movie_poster_image);
            cardView = (CardView) itemView.findViewById(R.id.cardView_single_movie);
        }
    }
}
