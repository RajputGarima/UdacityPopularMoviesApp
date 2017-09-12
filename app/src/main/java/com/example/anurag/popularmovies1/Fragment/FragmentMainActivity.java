package com.example.anurag.popularmovies1.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.anurag.popularmovies1.Api.Model.Result;
import com.example.anurag.popularmovies1.CardViewAdapter;
import com.example.anurag.popularmovies1.Adapter.MoviesAdapter;
import com.example.anurag.popularmovies1.R;
import com.example.anurag.popularmovies1.SettingsActivity;
import com.example.anurag.popularmovies1.VerticalSpace;
import com.example.anurag.popularmovies1.data.MoviesContract;

import java.util.List;

/**
 * Created by anurag on 10/9/2016.
 */

public class FragmentMainActivity extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int MOVIES_LOADER = 0;
    RecyclerView recyclerView;
    List<Result> list;
    SharedPreferences preferences;
    MoviesAdapter moviesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, container, false);
       // recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        moviesAdapter = new MoviesAdapter(getContext(), null, 0);
        GridView gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(moviesAdapter);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(MOVIES_LOADER, null, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private void setListToAdapter(List list) {
        CardViewAdapter adapter = new CardViewAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new VerticalSpace(5));


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            startActivity(new Intent(getContext(), SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

//        String value = preferences.getString("listPref", "popular");
        // getjson(value);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MoviesContract.MoviesEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        moviesAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        moviesAdapter.swapCursor(null);
    }


}
