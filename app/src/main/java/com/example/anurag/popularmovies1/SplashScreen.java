package com.example.anurag.popularmovies1;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.anurag.popularmovies1.Api.Model.MoviesModel;
import com.example.anurag.popularmovies1.Api.Model.Result;
import com.example.anurag.popularmovies1.Api.PopularMoviesApi;
import com.example.anurag.popularmovies1.data.MoviesContract.MoviesEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {
    List<Result> list;
    SharedPreferences preferences;
    String mListPref;
    LinearLayout mProgressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProgressLayout = (LinearLayout) findViewById(R.id.linearLayout);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        mListPref = preferences.getString("listPref", "popular");
        if (mListPref == null) {
            mListPref = "popular";
        }
        getjson(mListPref);
//        if (savedInstanceState == null) {
//            Log.d("prrr", "value is " + mListPref);
//
//
//        } else {
//            List<Result> listResult = savedInstanceState.getParcelableArrayList("list");
//            list = listResult;
//            Log.d("prrr", "called " + list.get(0).getTitle());
//
//        }

    }


    public void getjson(String sortBy) {
        mProgressLayout.setVisibility(View.VISIBLE);
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PopularMoviesApi popularMoviesApi = adapter.create(PopularMoviesApi.class);
        Call<MoviesModel> call = popularMoviesApi.getData(sortBy);
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()) {
                    MoviesModel moviesModel = response.body();
                    List<Result> getList = moviesModel.getResults();
                    listToContentValuesAndPutInDataBase(getList);
                    // setListToAdapter(list);
                    // Log.d("anurag", "list length" + list.size() + "title :" + list.get(0).getTitle() + "  image " + list.get(0).getPoster_path());


                } else {
                    Log.d("anurag", "negative");
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.d("anurag", "failed");
            }
        });

    }

    public void listToContentValuesAndPutInDataBase(List<Result> list) {
        Vector<ContentValues> cVVector = new Vector<ContentValues>(list.size());

        for (int i = 0; i < list.size(); i++) {
            Result curentResult = list.get(i);
            ContentValues movieValues = new ContentValues();
            movieValues.put(MoviesEntry.COLUMN_ID, curentResult.getId());
            movieValues.put(MoviesEntry.COLUMN_ADULT, curentResult.getAdult());
            movieValues.put(MoviesEntry.COLUMN_BACKDROP, curentResult.getBackdrop_path());
            movieValues.put(MoviesEntry.COLUMN_ORIGINAL_LANGUAGE, curentResult.getOriginal_language());
            movieValues.put(MoviesEntry.COLUMN_ORIGINAL_TITLE, curentResult.getOriginal_title());
            movieValues.put(MoviesEntry.COLUMN_TITLE, curentResult.getTitle());
            movieValues.put(MoviesEntry.COLUMN_OVERVIEW, curentResult.getOverview());
            movieValues.put(MoviesEntry.COLUMN_POPULARITY, curentResult.getPopularity());
            movieValues.put(MoviesEntry.COLUMN_POSTER_PATH, curentResult.getPoster_path());
            movieValues.put(MoviesEntry.COLUMN_RELEASE_DATE, curentResult.getRelease_date());
            movieValues.put(MoviesEntry.COLUMN_VOTE_AVERAGE, curentResult.getVote_average());
            cVVector.add(movieValues);
        }

        if (cVVector.size() > 0) {
            // Student: call bulkInsert to add the weatherEntries to the database here
            ContentValues[] cvArray = new ContentValues[cVVector.size()];
            cVVector.toArray(cvArray);
            getContentResolver().bulkInsert(MoviesEntry.CONTENT_URI, cvArray);

        }
        mProgressLayout.setVisibility(View.GONE);
        startActivity(new Intent(this, MainActivity.class));
        finish();


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // outState.putParcelableArrayList();
        //
        ArrayList<Result> arraylist = (ArrayList<Result>) list;
        outState.putParcelableArrayList("list", arraylist);

    }


}
