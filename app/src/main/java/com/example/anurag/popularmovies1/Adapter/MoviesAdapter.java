package com.example.anurag.popularmovies1.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.example.anurag.popularmovies1.R;
import com.example.anurag.popularmovies1.data.MoviesContract;
import com.squareup.picasso.Picasso;

/**
 * Created by anurag on 10/12/2016.
 */

public class MoviesAdapter extends CursorAdapter {

    Context mContext;

    public MoviesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185" +
                cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH))).
                into(viewHolder.posterView);
    }


    public static class ViewHolder {
        public static ImageView posterView;

        public ViewHolder(View view) {
            posterView = (ImageView) view.findViewById(R.id.movie_poster_image);


        }
    }
}
